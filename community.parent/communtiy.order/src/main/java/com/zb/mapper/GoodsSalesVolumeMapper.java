package com.zb.mapper;

import com.zb.pojo.GoodsSalesVolume;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/**
 * 计算商品销量
 * @author 孙硕威
 */
public interface GoodsSalesVolumeMapper {

    public int insertGoodsSalesVolume(GoodsSalesVolume goodsSalesVolume);

    public int updateGoodsSalesVolume(GoodsSalesVolume goodsSalesVolume);

    public int getGoodsSalesVolume(@PathVariable("goodsId") Integer goodsId);

}
