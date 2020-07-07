package com.zb.controller;

import com.netflix.discovery.converters.Auto;
import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.mapper.GoodsMapper;
import com.zb.pojo.Goods;
import com.zb.service.GoodsService;
import com.zb.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 分页查询商品信息
     * 1：本店热销 查询代纳普编号为1的热销商品{"isHotGoods":1,"index":1,"size":6,"storeId":1}
     * 2：本店新品 按时间排序查询新品{"index":1,"size":6,"putawayTime":1,"storeId":1}
     * 3: 多条件查询商品信息，并且查询图片,可以按销量排序：{"salesvolume":1}
     * 按评分排序{"putawayTime":1},按新品排序{"score":1},
     * 按照价格区间，仅显示有货，和是否参与活动
     * <p>
     * {  endPrice: ""
     * index: 1
     * salesvolume: 1
     * size: 12
     * startPrice: "" }
     *
     * @param goods
     * @return
     */
    @PostMapping(value = "/getGoodsPage")
    public Dto getGoodsPage(@RequestBody Goods goods) {

        try {
            PageUtil<Goods> pageUtil = goodsService.findAllGoodsByVo(goods);

            return DtoUtil.returnSuccess("message", pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查找所有的商品信息，给订单模块调用，计算销量
     *
     * @param goods
     * @return
     */
    @PostMapping(value = "/findAllGoodsForTiming")
    public List<Goods> findAllGoodsForTiming(@RequestBody Goods goods) {

        try {
            List<Goods> goodsList = goodsMapper.getGoodsList(goods);
            System.out.println(goodsList);
            return goodsList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping(value = "/updateGoods")
    public int updateGoods(@RequestBody Goods goods) {
        System.out.println("商品服务修改");
        try {
            return goodsMapper.updateGoods(goods);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
