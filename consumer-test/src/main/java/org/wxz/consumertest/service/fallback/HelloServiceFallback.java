package org.wxz.consumertest.service.fallback;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.wxz.consumertest.service.HelloService;


@Component
public class HelloServiceFallback implements HelloService  {

    @Override
    public String hello() {
        return "fall!";
    }
}
