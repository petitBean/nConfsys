package org.wxz.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@EnableFeignClients(basePackages = {"org.wxz.nconfsysauth"})
@SpringBootApplication
@ComponentScan(basePackages = {"org.wxz.confsysdomain","org.wxz.nconfsyscommon","org.wxz.demo","org.wxz.confsysserviceapi"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
