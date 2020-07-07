package com.zb.service;

public interface QgPreparatoryWorkService {

    /**
     * 定时任务
     * 抢购准备工作
     * 去抢购表查询抢购商品的库存，计算完库存以后存进redis
     */
    public void qgGoodsToRedis();

    /**
     * 去redis检查这个房间还有库存吗
     *
     * @return return 0:没有库存； return 1：有库存
     */
    public int checkGoodsInventory(Integer goodsId);
}
