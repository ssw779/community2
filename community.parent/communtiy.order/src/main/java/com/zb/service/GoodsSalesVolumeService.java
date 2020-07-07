package com.zb.service;

public interface GoodsSalesVolumeService {
    /**
     * 往商品销量表里添加数据，如果存在就修改，如果不存在就添加
     * @param goodsId 商品编号
     * @param salesVolume 销量
     * @return
     */
    public void disposeGoodsSalesVolume(Integer goodsId,Integer salesVolume);
}
