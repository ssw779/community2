package com.zb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心启动类
 *
 * @author 孙硕威
 */
@SpringBootApplication
@EnableEurekaServer
public class EuerkaApp {
    public static void main(String[] args) {
        SpringApplication.run(EuerkaApp.class, args);

    }
}
