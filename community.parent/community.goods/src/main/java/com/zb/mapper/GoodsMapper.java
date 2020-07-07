package com.zb.mapper;

import com.zb.pojo.Goods;
import com.zb.vo.StoreVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商品Mapper
 *
 * @author 孙硕威
 */
@Mapper
public interface GoodsMapper {
    /**
     * 商品唯一查询
     *
     * @param id 商品编号
     * @return 返回商品的全部信息
     * @throws Exception
     */
    public Goods getGoodsById(@Param(value = "id") Long id) throws Exception;

    /**
     * 商品的全部查询，按条件查询、分页查询
     *
     * @param goods
     * @return 商品的集合
     * @throws Exception
     */
    public List<Goods> getGoodsList(Goods goods) throws Exception;

    /**
     * 查询分页数据count
     *
     * @param goods
     * @return 返回有几条分页数据 int
     * @throws Exception
     */
    public Integer getGoodsCount(Goods goods) throws Exception;

    /**
     * 添加商品信息
     *
     * @param goods
     * @return 返回受影响行数
     * @throws Exception
     */
    public Integer insertGoods(Goods goods) throws Exception;

    /**
     * 修改商品信息
     *
     * @param goods
     * @return 返回受影响行数
     * @throws Exception
     */
    public Integer updateGoods(Goods goods) throws Exception;


}
