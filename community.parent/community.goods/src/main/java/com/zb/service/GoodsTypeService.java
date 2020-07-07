package com.zb.service;

import com.zb.pojo.Goodstype;

import java.util.List;

public interface GoodsTypeService {
    /**
     * 查询分类
     * @param goodstype
     * @return 返回一个分类的集合
     */
    public List<Goodstype> getShoptypeListByPojo(Goodstype goodstype) throws Exception;
}
