package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zb.config.OrderDelayRabbitConfig;
import com.zb.feign.GoodsFeignClient;
import com.zb.feign.TemGoodsFeignClient;
import com.zb.mapper.OrdersMapper;
import com.zb.pojo.CommunityTask;
import com.zb.pojo.Orders;
import com.zb.pojo.QgGoods;
import com.zb.pojo.TempGoods;
import com.zb.service.CommunityTaskService;
import com.zb.service.OrdersService;
import com.zb.util.IdWorker;
import com.zb.util.RedisUtil;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private TemGoodsFeignClient temGoodsFeignClient;
    @Autowired
    private RedisUtil redisUtils;
    @Autowired
    private CommunityTaskService communityTaskService;


    @Override
    public Orders getGoodsSalesVolume(Integer goodsId, Integer storeId) {
        Orders orders = ordersMapper.getGoodsSalesVolume(storeId, goodsId);
        System.out.println("orders" + orders);
        return orders;
    }

    @Override
    public int insertOrders(Orders orders) {
        int count = ordersMapper.insertOrders(orders);
        if (count > 0) {
            Map<String, Object> mqData = new HashMap<>();
            mqData.put("ordersNo", orders.getOrderNo());
            //发送消息队列，超过一定时间，进入死信队列，处理
            amqpTemplate.convertAndSend(OrderDelayRabbitConfig.ORDER_EXCHANGE_NAME, OrderDelayRabbitConfig.ORDER_ROUTING_KEY, mqData, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    //设置失效时间
                    message.getMessageProperties().setExpiration(60 * 2000 + "");
                    return message;
                }
            });
        }

        return 0;
    }

    /**
     * 监听insertOrders（）方法发送的队列，监听过了时间没有付款的信息
     *
     * @param param
     * @param message
     * @param channel
     */
    @RabbitListener(queues = OrderDelayRabbitConfig.ORDER_DELAY_QUEUE)
    public void monitorOrderDelay(Map<String, Object> param, Message message, Channel channel) {
        System.out.println("延迟队列");
        String ordersNo = param.get("ordersNo").toString();
        System.out.println(ordersNo);
        //根据订单编号查询订单状态
        Orders orders = ordersMapper.getOrdersById(ordersNo);
        if (orders.getOrderStatus() == 0) {
            //修改订单状态为取消支付
            Orders orders1 = new Orders();
            orders1.setOrderNo(ordersNo);
            //设置状态为已取消
            orders1.setOrderStatus(3);
            int row = ordersMapper.updateOrders(orders1);
            if (row > 0) {
                //修改临时库存表
                TempGoods tempGoods = new TempGoods();
                //哪个用户
                tempGoods.setCreatedBy(orders.getUserId().toString());
                //哪个商品
                tempGoods.setGoodsId(Long.parseLong(orders.getGoodsNo().toString()));
                //订单状态为30后未支付
                tempGoods.setStatus(3);
                int count = temGoodsFeignClient.updateTempGoods(tempGoods);
                if (count > 0) {
                    //修改redis库存
                    String key = "qgGoods" + orders.getGoodsNo();
                    String strList = redisUtils.get(key).toString();
                    QgGoods qgGoods = JSON.parseObject(strList, QgGoods.class);
                    //更改redis里的库存
                    qgGoods.setInventory(qgGoods.getInventory() + 1);
                    //重新存进redis
                    redisUtils.set(key, JSON.toJSONString(qgGoods));
                } else {
                    System.out.println("临时库存表不需要修改");
                }
            }

        } else {
            System.out.println("订单不需要修改");
        }
    }

    @Override
    public int updateOrders(Orders orders) {
        int count = ordersMapper.updateOrders(orders);
        if (count > 0) {
            //往临时任务表添加信息
            CommunityTask communityTask = new CommunityTask();
            communityTask.setId(IdWorker.getId());
            communityTask.setMqExchange("ex_learning_addchoosecourse");
            communityTask.setMqRoutingkey("addchoosecourse");
            communityTask.setStatus(1 + "");
            communityTask.setTaskType(1 + "");
            communityTask.setVersion(1);
            Map<String, Object> requestBody = new HashMap<>();
            //用户编号
            requestBody.put("userId", orders.getUserId());
            //订单编号
            requestBody.put("goodsId", orders.getOrderNo());
            requestBody.put("price", orders.getPayAmount());
            requestBody.put("createtTime", orders.getCreateTime());
            requestBody.put("finshTime", orders.getFinshTime());
            communityTask.setRequestBody(JSON.toJSONString(requestBody));
            communityTaskService.insertTask(communityTask);
        }

        return count;
    }


    @Override
    public Orders getOrdersById(String orderNo) {

        return ordersMapper.getOrdersById(orderNo);
    }
}
