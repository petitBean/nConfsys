package org.wxz.confsystest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class RibbonConfig {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
