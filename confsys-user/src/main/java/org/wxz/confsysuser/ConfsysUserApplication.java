package org.wxz.confsysuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ComponentScan(basePackages = {"org.wxz.confsysdomain","org.wxz.confsysuser"})
public class ConfsysUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfsysUserApplication.class, args);
    }

}
