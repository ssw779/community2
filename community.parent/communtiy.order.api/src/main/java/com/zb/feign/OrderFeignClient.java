package com.zb.feign;

import com.zb.pojo.Orders;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "ordersServer")
public interface OrderFeignClient {

    @PostMapping(value = "/orders/insertOrders")
    public int insertOrders(@RequestBody Orders orders);


    @PostMapping(value = "/orders/updateOrders")
    public int updateOrders(@RequestBody Orders orders);
}
