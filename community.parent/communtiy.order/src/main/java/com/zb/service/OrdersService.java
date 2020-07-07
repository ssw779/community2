package com.zb.service;

import com.zb.pojo.Orders;
import org.springframework.stereotype.Service;

@Service
public interface OrdersService {
    /**
     * 根据商品编号和店铺编号查询销量
     *
     * @param goodsId 商品编号
     * @param storeId 店铺编号
     * @return 返回订单信息，商品编号和商品的销售量
     */
    public Orders getGoodsSalesVolume(Integer goodsId, Integer storeId);

    /**
     * 添加订单信息
     * 同时发送消息队列
     * @param orders
     * @return
     */
    public int insertOrders(Orders orders);


    /**
     * 修改订单信息 同时将订单信息添加到任务表中
     *
     * @param orders
     * @return
     */
    public int updateOrders(Orders orders);

    /**
     * 根据订单编号，查找完整订单信息
     *
     * @param orderNo
     * @return
     */
    public Orders getOrdersById(String orderNo);
}
