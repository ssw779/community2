package com.zb.feign;


import com.zb.pojo.Goods;
import com.zb.pojo.Goodstype;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "goodsServer")
public interface GoodsFeignClient {
    @GetMapping(value = "/goods/findGoodsType")
    /**
     * 查询商品类型
     */
    public List<Goodstype> findGoodsType(@RequestBody Goodstype goodstype);


    @PostMapping(value = "/goods/findAllGoodsForTiming")
    public List<Goods> findAllGoodsForTiming(@RequestBody Goods goods);

    @PostMapping(value = "/goods/updateGoods")
    public int updateGoods(@RequestBody Goods goods);


}
