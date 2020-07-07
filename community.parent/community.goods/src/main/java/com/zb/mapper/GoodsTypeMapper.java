package com.zb.mapper;

import com.zb.pojo.Goodstype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsTypeMapper {

    public Goodstype getShoptypeById(@Param(value = "id") Long id) throws Exception;

    public List<Goodstype> getShoptypeListByPojo(Goodstype goodstype) throws Exception;


    public Integer insertShoptype(Goodstype goodstype) throws Exception;

    public Integer updateShoptype(Goodstype goodstype) throws Exception;


}
