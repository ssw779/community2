package com.zb.controller;

import com.zb.service.QgGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QgGoodsController {
    @Autowired
    private QgGoodsService qgGoodsService;

    /**
     * 用户抢购方法
     * 只是添加一条预定信息，并没有往订单里添加数据
     *
     * @param goodsId 商品的编号 不是抢购的编号
     * @param token
     * @return
     */
    @GetMapping(value = "/qgGoods/{goodsId}/{token}")
    public String qgGoods(@PathVariable("goodsId") Integer goodsId, @PathVariable("token") String token) {

        return qgGoodsService.qgGoods(goodsId, token);
    }

    /**
     * 查询抢购结果的方法 页面轮询调取此方法
     *
     * @param goodsId
     * @param token
     * @return
     */
    @GetMapping(value = "/findQgResult/{goodsId}/{token}")
    public String findQgResult(@PathVariable("goodsId") Integer goodsId, @PathVariable("token") String token) {
        return qgGoodsService.findQgResult(token, goodsId);
    }

}
