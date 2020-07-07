package com.zb.controller;

import com.zb.pojo.TempGoods;
import com.zb.service.TempGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/temp")
public class TempGoodsController {


    @Autowired
    private TempGoodsService tempGoodsService;

    /**
     * 修改临时库存表
     * @param tempGoods
     * @return
     */
    @PostMapping(value = "/updateTempGoods")
    public int updateTempGoods(@RequestBody TempGoods tempGoods) {

        return tempGoodsService.updateTempGoods(tempGoods);
    }

    /**
     * 获取临时库存表的信息
     * @param tempGoods
     * @return
     */
    @PostMapping(value = "/getTempGoods")
    public TempGoods getTempGoods(@RequestBody TempGoods tempGoods) {

        return tempGoodsService.getTempGoods(tempGoods);
    }
}
