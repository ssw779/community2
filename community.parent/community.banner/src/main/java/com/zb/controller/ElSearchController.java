package com.zb.controller;

import com.zb.pojo.Store;
import com.zb.service.ElSearchStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ElSearchController {
    @Autowired
    private ElSearchStoreService elSearchStoreService;

    /**
     * 搜索引擎查询店铺信息，根据关键字
     * @param keyWord
     * @return
     */
    @GetMapping(value = "/elSearchStore")
    public List<Store> elSearchStore(String keyWord) {
        try {
            return elSearchStoreService.elSearchStore(keyWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
