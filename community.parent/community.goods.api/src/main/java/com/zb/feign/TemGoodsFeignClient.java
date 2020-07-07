package com.zb.feign;

import com.zb.pojo.TempGoods;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "goodsServer")
public interface TemGoodsFeignClient {

    @PostMapping(value = "/temp/updateTempGoods")
    public int updateTempGoods(@RequestBody TempGoods tempGoods);


    @PostMapping(value = "/temp/getTempGoods")
    public TempGoods getTempGoods(@RequestBody TempGoods tempGoods);
}
