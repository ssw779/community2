package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.mapper.QgGoodsMapper;
import com.zb.mapper.TempGoodsMapper;
import com.zb.pojo.QgGoods;
import com.zb.service.QgPreparatoryWorkService;
import com.zb.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QgPreparatoryWorkServiceImpl implements QgPreparatoryWorkService {

    @Autowired
    private QgGoodsMapper qgGoodsMapper;
    @Autowired
    private TempGoodsMapper tempGoodsMapper;
    @Autowired
    private RedisUtil redisUtils;

    @Scheduled(cron = "00 00 00 * * ?")
    @Override
    public void qgGoodsToRedis() {
        //先查询抢购表商品的库存，再查询临时记录表里的条数，相减得出可抢购商品，存进redis
        List<QgGoods> qgGoodsList = qgGoodsMapper.getQgGoodsList(null);
        for (QgGoods qgGoods : qgGoodsList) {
            //根据商品编号，和店铺编号获取得
            Map<String,Object>param=new HashMap<>();
            param.put("goodsId",qgGoods.getGoodsNo());
            param.put("storeId",qgGoods.getStoreNo());
            int count = tempGoodsMapper.getTempGoodsCount(param);
            //原始库存，减去临时库存表里得库存，得实际库存
            qgGoods.setInventory(qgGoods.getInventory() - count);

            redisUtils.set("qgGoods" + qgGoods.getGoodsNo(), JSON.toJSONString(qgGoods));
        }
    }

    @Override
    public int checkGoodsInventory(Integer goodsId) {
        String key = "qgGoods" + goodsId;
        if (redisUtils.hasKey(key)) {
            String toString = redisUtils.get(key).toString();
            QgGoods qgGoods = JSON.parseObject(toString, QgGoods.class);
            if (qgGoods.getInventory() > 0) {
                return 1;
            }

        }
        return 0;
    }
}
