package com.zb.controller;

import com.zb.pojo.Orders;
import com.zb.service.OrdersService;
import com.zb.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/orders")
public class OrdersController {


    @Autowired
    private OrdersService ordersService;

    @PostMapping(value = "/insertOrders")
    public int insertOrders( Orders orders) {
        orders.setOrderNo(IdWorker.getId());
        int row = ordersService.insertOrders(orders);
        if (row > 0) {
            //添加成功
        }
        //添加失败
        return 0;

    }

    @PostMapping(value = "/updateOrders")
    public int updateOrders(@RequestBody Orders orders) {
        return ordersService.updateOrders(orders);
    }
}
