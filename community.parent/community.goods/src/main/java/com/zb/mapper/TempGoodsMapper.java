package com.zb.mapper;

import com.zb.pojo.TempGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TempGoodsMapper {
    /**
     * 根据商品编号，店铺编号 查询有几条记录
     * @param param
     * @return
     */
    public int getTempGoodsCount(Map<String,Object>param);

    public TempGoods getTempGoods(TempGoods tempGoods);


    /**
     * 添加临时库存表
     * @param tempGoods
     * @return
     */
    public Integer insertCommunityTempGoods(TempGoods tempGoods);

    /**
     * 修改临时库存表
     * @param tempGoods
     * @return
     */
    public Integer updateCommunityTempGoods(TempGoods tempGoods);

}
