package com.zb.service;

import com.zb.pojo.TempGoods;

/**
 * 商品临时库存
 *
 * @author 孙硕威
 */
public interface TempGoodsService {
    /**
     * 修改临时库存表
     *
     * @param tempGoods
     * @return
     */
    public int updateTempGoods(TempGoods tempGoods);

    /**
     * 查询临时库存表信息
     * @param tempGoods
     * @return
     */
    public TempGoods getTempGoods(TempGoods tempGoods);
}
