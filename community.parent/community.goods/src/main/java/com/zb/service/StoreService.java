package com.zb.service;

import com.zb.pojo.Store;
import com.zb.vo.StoreVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreService {
    /**
     * 来个定时任务，在零点修改商家评分
     */

    /**
     * 搜索引擎查找店铺
     * @return
     */
    public List<Store>elsGetStore();


    /**
     * 多条件查询店铺，包括店铺图片
     *
     * @param goodsVo
     * @return
     * @throws Exception
     */
    public List<Store> getStoreListByVo(StoreVo goodsVo) throws Exception;

    /**
     * 根据编号查找店铺信息
     *
     * @param id 店铺编号
     * @return
     * @throws Exception
     */
    public Store getStoreById( Integer id) throws Exception;
}
