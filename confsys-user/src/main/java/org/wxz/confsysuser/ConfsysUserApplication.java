package org.wxz.confsysuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.wxz.confsysdomain","org.wxz.confsysuser"})
public class ConfsysUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfsysUserApplication.class, args);
    }

}
