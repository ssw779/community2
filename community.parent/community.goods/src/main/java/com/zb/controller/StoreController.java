package com.zb.controller;

import com.netflix.discovery.converters.Auto;
import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.pojo.Image;
import com.zb.pojo.Store;
import com.zb.service.ImageService;
import com.zb.service.StoreService;
import com.zb.vo.StoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 店铺的controller层
 *
 * @author 孙硕威
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @PostMapping(value = "/findAllStore")
    /**
     * 查询店铺信息，同时查询店铺的图片
     * 这里传的值具体以前台为准 json类型
     * {
     * "index":0, 分页起始位置
     * "size":6, 总共显示几条数据
     * "imageType":1, 图片类型 1：店铺图片
     * "imagePostion":1, 图片位置 1：首页显示
     * "shopParenType":1, 商品父级编号 1：餐饮
     * "isHotStore":1, 是不是热门店铺
     * "shopType":null, 商品类型，传null就是全部查询
     * "storeType":1 店铺分类，普通店铺还是家政店铺
     * }
     */
    public Dto findAllStore(@RequestBody StoreVo storeVo) {
        try {

            List<Store> storeList = storeService.getStoreListByVo(storeVo);
            return DtoUtil.returnSuccess("success", storeList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping(value = "/getStoreById/{id}")
    public Dto getStoreById(@PathVariable("id") Integer id) {
        try {
            Store store = storeService.getStoreById(id);
            return DtoUtil.returnSuccess("success", store);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/elsGetStore")
    public List<Store> elsGetStore() {

        List<Store> storeList = storeService.elsGetStore();
        System.out.println(storeList);
        return storeList;

    }


}
