package com.zb.service;

public interface QgGoodsService {


    /**
     * 点击完以后调用的方法
     * 点击预定或者购买，往临时库存表添加一条记录
     * 修改redis里的库存
     *
     * @param goodsId 哪条商品信息
     * @param userId  哪个用户操作的
     * @return
     */
    public int lockQgGoods(Integer goodsId, Integer userId);

    /**
     * 抢购商品的方法
     * <p>
     * 使用自定义的分布式锁 ，完成高并发下的抢购操作
     * 为什么要用消息队列，因为为了避免多线程情况下产生大量的线程等待，从而采用rabbitmq
     *
     * @param goodsId 哪个商品(抢购商品的编号，不是商品的编号)
     * @param token   哪个用户 这个token是获取到浏览器里的用户token
     * @return
     */
    public String qgGoods(Integer goodsId, String token);


    /**
     *查询抢购结果 看看有没有抢购成功
     * @param token 用户token 判断是哪个用户
     * @param goodsId 商品编号 哪个商品
     * @return
     */
    public String findQgResult(String token ,Integer goodsId);
}
