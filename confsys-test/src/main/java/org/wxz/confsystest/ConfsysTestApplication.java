package org.wxz.confsystest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConfsysTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfsysTestApplication.class, args);
    }

}
