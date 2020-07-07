package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zb.config.DelayRabbitConfig;
import com.zb.config.MqConfig;
import com.zb.mapper.QgGoodsMapper;
import com.zb.mapper.TempGoodsMapper;
import com.zb.pojo.QgGoods;
import com.zb.pojo.TempGoods;
import com.zb.service.QgGoodsService;
import com.zb.service.QgPreparatoryWorkService;
import com.zb.util.RedisUtil;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QgGoodsServiceImpl implements QgGoodsService {

    @Autowired
    private TempGoodsMapper tempGoodsMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private RedisUtil redisUtils;
    @Autowired
    private QgPreparatoryWorkService qgPreparatoryWorkService;


    @Override
    public int lockQgGoods(Integer goodsId, Integer userId) {
        String key = "qgGoods" + goodsId;
        //从redis获取抢购商品信息
        String toString = redisUtils.get(key).toString();
        QgGoods qgGoods = JSON.parseObject(toString, QgGoods.class);
        //往临时库存表添加一条信息
        TempGoods tempGoods = new TempGoods();
        //店铺编号
        tempGoods.setStoreId(qgGoods.getStoreNo());
        //商品编号(抢购商品编号)
        tempGoods.setGoodsId(Long.parseLong(goodsId.toString()));
        //哪个用户
        tempGoods.setCreatedBy(userId.toString());
        //设置状态为预定，1：预定2：已支付3：30分钟后未支付
        tempGoods.setStatus(1);
        //更改redis里的库存
        qgGoods.setInventory(qgGoods.getInventory() - 1);
        //重新存进redis
        redisUtils.set(key, JSON.toJSONString(qgGoods));
        //往临时库存表添加信息
        return tempGoodsMapper.insertCommunityTempGoods(tempGoods);

    }

    @Override
    public String qgGoods(Integer goodsId, String token) {
        Map<String, Object> param = new HashMap<>();
        param.put("goodsId", goodsId);
        param.put("token", token);
        amqpTemplate.convertAndSend(MqConfig.myexchange, "qg.goods", param);
        return "正在排队，请稍后";
    }

    @Override
    public String findQgResult(String token, Integer goodsId) {
        //查找用户token，是从redis里查找，看看有没有该用户的信息
        //User user=userFeign.gindUser(token);
        //这个1是user.getId();
        String qgKey = "qg_goods" + 1 + ":" + goodsId;
        //查看这个商品还有没库存 去redis里查
        int count = qgPreparatoryWorkService.checkGoodsInventory(goodsId);
        if (count == 0) {
            return "抢购失败，没有库存";
        }

        if (redisUtils.hasKey(qgKey)) {

            return "抢购成功";
        } else {

            return "抢购失败";
        }


    }

    /**
     * 监听qgGoods（）发送过来的Mq队列
     *
     * @param param   提供者传过来的参数
     * @param message
     * @param channel
     */
    @RabbitListener(queues = MqConfig.myqueue)
    public void monitorMq(Map<String, Object> param, Message message, Channel channel) {
        Integer goodsId = Integer.parseInt(param.get("goodsId").toString());
        String token = param.get("token").toString();
        System.out.println("goodsId:" + goodsId + "\t" + "token:" + token);
        //根据token获取用户的信息，如果存在就继续
        /**
         *  User user = userFeignClient.getUserByTokenFromRedis(token);
         *         if (user == null) {
         *             return;
         *         }
         */
        //避免多线程情况下抢超，抢多，采用分布式锁，给公共资源加锁
        String lock = "lock" + goodsId;
        try {
            //自旋，循环获取锁
            while (!redisUtils.lock(lock)) {
                Thread.sleep(20);

            }
            //检查有没有此商品
            String qgGoodsKey = "qgGoods" + goodsId;
            if (!redisUtils.hasKey(qgGoodsKey)) {
                return;
            }

            //检查库存，检查的是redis里的库存
            System.out.println("检查库存，检查的是redis里的库存");
            int stock = qgPreparatoryWorkService.checkGoodsInventory(goodsId);
            if (stock == 0) {

                return;
            }
            //判断用户是不是第二次抢购
            System.out.println("判断用户是不是第二次抢购");
            //根据抢购商品编号，获取商品编号
            Map<String, Object> countMap = new HashMap<>();
            countMap.put("goodsId", goodsId);
            countMap.put("createdBy", 1);
            int count = tempGoodsMapper.getTempGoodsCount(countMap);
            if (count > 0) {
                return;
            }
            //执行预定，并且修改redis里的库存
            System.out.println("执行预定，并且修改redis里的库存");
            int lockQgGoods = this.lockQgGoods(goodsId, 1);
            //如果往临时表里添加信息成功就修改redis库存，向redis写入抢购成功的用户信息
            if (lockQgGoods > 0) {
                //1是用户id调用其他模块
                String qgKey = "qg_goods" + 1 + ":" + goodsId;
                //存进redis 5分钟后不支付，修改订单状态，恢复库存
                redisUtils.hmset(JSON.toJSONString(qgKey), param, 60 * 5);
                //把抢购的用户的信息，和商品信息发到死信队列上，过了一段时间没有付款，就修改状态
                Map<String, Object> mqData = new HashMap<>();
                mqData.put("userId", 1);
                mqData.put("goodsId", goodsId);
                System.out.println("延时队列：5分钟后检查订单状态");
                amqpTemplate.convertAndSend(DelayRabbitConfig.ORDER_EXCHANGE_NAME, DelayRabbitConfig.ORDER_ROUTING_KEY, mqData, new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        //设置失效时间
                        message.getMessageProperties().setExpiration(60 * 2000 + "");
                        return message;
                    }
                });
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            redisUtils.unLock(lock);
        }


    }

    /**
     * 监听死信队列，说明5分钟还没有去支付，去付钱，就修改状态，这里只处理点击抢购按钮以后的事情
     * 如果状态还没有更改为待支付或者已付款，就修改状态为30分钟后未支付，即：取消订单
     *
     * @param param
     * @param message
     * @param channel
     */
    @RabbitListener(queues = DelayRabbitConfig.ORDER_DELAY_QUEUE)
    public void monitorDelay(Map<String, Object> param, Message message, Channel channel) {
        System.out.println("临时库存表的延迟队列");
        System.out.println("5分钟之后获取到临时库存的状态信息");
        String goodsId = param.get("goodsId").toString();
        String userId = param.get("userId").toString();
        //根据商品编号和用户编号查找订单状态，如果订单状态还是为1，即表示5分钟后没有下订单
        //即修改临时库存表的状态 并且修改redis的库存
        //封装查询对象
        TempGoods tempGoods = new TempGoods();
        //商品编号
        tempGoods.setGoodsId(Long.parseLong(goodsId));
        //用户编号
        tempGoods.setCreatedBy(userId);
        //根据用户编号和商品编号查到临时库存表信息
        TempGoods tempGoods1 = tempGoodsMapper.getTempGoods(tempGoods);
        if (tempGoods1.getStatus() == 1) {
            //说明状态还是预定，没有执行任何的操作
            //修改临时状态表
            //封装修改的对象
            TempGoods updateTempGoods = new TempGoods();
            updateTempGoods.setId(tempGoods1.getId());
            //把状态修改为30分钟后未支付
            updateTempGoods.setStatus(3);
            Integer count = tempGoodsMapper.updateCommunityTempGoods(updateTempGoods);
            if (count > 0) {
                //修改数据库成功
                String key = "qgGoods" + goodsId;
                String strList = redisUtils.get(key).toString();
                QgGoods qgGoods = JSON.parseObject(strList, QgGoods.class);
                //更改redis里的库存
                qgGoods.setInventory(qgGoods.getInventory() + 1);
                //重新存进redis
                redisUtils.set(key, JSON.toJSONString(qgGoods));
                System.out.println("修改库存完毕");
            }

        } else {
            System.out.println("不需要修改");
            return;
        }
    }
}
