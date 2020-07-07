package com.zb.mapper;

import com.zb.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrdersMapper {
    /**
     * 从订单表计算出商品销量
     * @return
     * @param storeNo 店铺编号
     * @param goodsNo 商品编号
     */
    public Orders getGoodsSalesVolume(@Param("storeNo") Integer storeNo, @Param("goodsNo")Integer goodsNo);

    /**
     * 订单唯一查询
     * @param
     * @return
     */
    public Orders getOrdersById(/*@Param("id") Integer id,*/@Param("orderNo") String orderNo);

    /**
     * 查询订单集合的方法
     * @param orders
     * @return
     */
    public List<Orders>getOrdersList(Orders orders);

    /**
     * 添加订单信息
     * @param orders
     * @return
     */
    public int insertOrders(Orders orders);

    /**
     * 修改订单
     * @param orders
     * @return
     */
    public int updateOrders(Orders orders);
}
