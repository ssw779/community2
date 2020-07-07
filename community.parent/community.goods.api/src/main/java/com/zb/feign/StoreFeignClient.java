package com.zb.feign;


import com.zb.pojo.Store;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "goodsServer")
public interface StoreFeignClient {
    @GetMapping(value ="/store/elsGetStore")
    public List<Store> elsGetStore();
}
