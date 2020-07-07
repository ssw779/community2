package com.zb.controller;


import com.zb.pojo.Goodstype;
import com.zb.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/goodsType")
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    @PostMapping(value = "/findGoodsType")
    /**
     * 查询商品类型
     */
    public List<Goodstype> findGoodsType(@RequestBody Goodstype goodstype) {

        try {
            return goodsTypeService.getShoptypeListByPojo(goodstype);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
