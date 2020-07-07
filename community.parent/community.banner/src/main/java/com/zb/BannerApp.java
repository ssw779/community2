package com.zb;

import com.zb.tools.CanalTools;
import com.zb.tools.Estools;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 别忘了开启canal服务端
 * startup.bat
 * d:canal/bin/startup.bat
 * 还有
 *D:\openresty-1.15.8.2-win64 nginx.exe
 * @author 孙硕威
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BannerApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BannerApp.class, args);
        Estools estools = run.getBean(Estools.class);
        CanalTools bean = run.getBean(CanalTools.class);
        //修改数据库时修改redis里存储的数据
        bean.execution();
        try {
              //estools.addIndex();
            //estools.addDoc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Bean
    public RestTemplate createRestTemplate() {

        return new RestTemplate();
    }
}
