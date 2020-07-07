package com.zb.mapper;

import com.zb.pojo.Store;
import com.zb.vo.StoreVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StoreMapper {

    /**
     * elsearch的店铺全部查询
     * 包括店铺的图片
     *
     * @return
     */
    public List<Store> elsGetStore();


    /**
     * 根据编号查找店铺信息
     *
     * @param id
     * @return 一个商店信息
     * @throws Exception
     */
    public Store getStoreById(@Param(value = "id") Integer id) throws Exception;

    /**
     * 多条件查找店铺信息，分页
     *
     * @param storeVo
     * @return 店铺集合
     * @throws Exception
     */
    public List<Store> getStoreListByMap(StoreVo storeVo) throws Exception;

    /**
     * 获取分页数据的条数
     *
     * @param storeVo
     * @return
     * @throws Exception
     */
    public Integer getStoreCountByMap(StoreVo storeVo) throws Exception;

    /**
     * 添加商铺信息
     *
     * @param store
     * @return 返回受影响行数
     * @throws Exception
     */
    public Integer insertStore(Store store) throws Exception;

    /**
     * 修改商铺信息
     *
     * @param store
     * @return 返回受影响行数
     * @throws Exception
     */
    public Integer updateStore(Store store) throws Exception;


}
