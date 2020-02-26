package org.wxz.consumertest.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wxz.consumertest.service.fallback.HelloServiceFallback;


@FeignClient(value = "client-test1",fallback = HelloServiceFallback.class)
public interface HelloService {
    @RequestMapping(value = "/hello/hello_word")
    String hello();
}
