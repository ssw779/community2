package com.zb;

import com.zb.controller.OrdersController;
import com.zb.service.OrdersService;
import com.zb.service.impl.GoodsSalesVolumeServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.zb.mapper")
@EnableScheduling
public class OrderApp {
    public static void main(String[] args) {
         SpringApplication.run(OrderApp.class, args);

    }
}
