package com.zb.service;

import com.zb.pojo.Store;

import java.util.List;

public interface ElSearchStoreService {
    /**
     * 搜索引擎 查询店铺信息
     * @param keyWord 店铺名关键字
     * @return
     * @throws Exception
     */
    public List<Store>elSearchStore(String keyWord)throws Exception;
}
