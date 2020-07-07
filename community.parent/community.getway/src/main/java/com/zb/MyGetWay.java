package com.zb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 通过localhost:8003访问网关
 *
 * @author 孙硕威
 */
@SpringBootApplication
@EnableZuulProxy//开启zuul网关
public class MyGetWay {
    public static void main(String[] args) {
        SpringApplication.run(MyGetWay.class, args);
    }
}
