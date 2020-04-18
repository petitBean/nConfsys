package org.wxz.confserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ComponentScan(basePackages = {"org.wxz.confsysdomain","org.wxz.nconfsyscommon","org.wxz.confsysserviceapi","org.wxz.confserver"})
public class ConfServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfServerApplication.class, args);
    }

}
