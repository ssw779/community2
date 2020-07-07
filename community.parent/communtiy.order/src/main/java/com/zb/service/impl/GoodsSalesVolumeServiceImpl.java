package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.dto.Dto;
import com.zb.feign.GoodsFeignClient;
import com.zb.mapper.GoodsSalesVolumeMapper;
import com.zb.pojo.Goods;
import com.zb.pojo.GoodsSalesVolume;
import com.zb.pojo.Orders;
import com.zb.service.GoodsSalesVolumeService;
import com.zb.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class GoodsSalesVolumeServiceImpl implements GoodsSalesVolumeService {
    @Autowired
    private GoodsSalesVolumeMapper goodsSalesVolumeMapper;
    @Autowired
    private GoodsFeignClient goodsFeignClient;
    @Autowired
    private OrdersService ordersService;

    /**
     * 每隔十分钟
     * 定时查看每个商品的销量，并且添加到销量表里面去
     *
     * @return
     */
    //@Scheduled(cron = "0 */10 * * * ?")
    public int timingInsertInfo() {

        Goods goods = new Goods();
        //获取全部的商品信息
        List<Goods> goodsList = goodsFeignClient.findAllGoodsForTiming(goods);
        for (Goods goods1 : goodsList) {
            //根据商品编号，和店铺编号查出商品编号和销售量
            Orders orders = ordersService.getGoodsSalesVolume(goods1.getId(), goods1.getStoreId());
            if (orders != null) {
                goods.setId(orders.getGoodsNo());
                goods.setSalesvolume(orders.getSalesVolume());
                //修改商品表的销量
                int count = goodsFeignClient.updateGoods(goods);
                System.out.println("count"+count);
                //操作销量表，存在就修改，不存在就添加
                disposeGoodsSalesVolume(orders.getGoodsNo(), orders.getSalesVolume());
            }
        }
        return 1;


    }

    @Override
    public void disposeGoodsSalesVolume(Integer goodsId, Integer salesVolume) {

        System.out.println("进入操作方法");
        GoodsSalesVolume goodsSalesVolume = new GoodsSalesVolume();
        goodsSalesVolume.setGoodsId(goodsId);
        goodsSalesVolume.setSalesVolume(salesVolume);
        int count = goodsSalesVolumeMapper.getGoodsSalesVolume(goodsId);
        if (count > 0) {
            goodsSalesVolumeMapper.updateGoodsSalesVolume(goodsSalesVolume);
        } else {
            goodsSalesVolumeMapper.insertGoodsSalesVolume(goodsSalesVolume);
        }

    }
}
