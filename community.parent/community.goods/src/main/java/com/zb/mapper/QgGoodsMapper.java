package com.zb.mapper;

import com.zb.pojo.QgGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QgGoodsMapper {



	public List<QgGoods> getQgGoodsList(QgGoods qgGoods);

	public Integer getQgGoodsCount(QgGoods qgGoods);

	public Integer insertQgGoods(QgGoods qgGoods);

	public Integer updateQgGoods(QgGoods qgGoods);



}
