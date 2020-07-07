package com.zb.service;

import com.zb.pojo.Goods;
import com.zb.util.PageUtil;

import java.util.List;

public interface GoodsService {
    /**
     * 查询商品的信息 分页查询
     * 根据商品的编号，去查商品的图片信息
     *
     * @param goods
     * @return
     * @throws Exception
     */
    public PageUtil<Goods> findAllGoodsByVo(Goods goods) throws Exception;

    /**
     * 修改商品
     * @param goods
     * @return
     * @throws Exception
     */
    public int updateGoods(Goods goods) throws Exception;
}
