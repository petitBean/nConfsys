package org.wxz.consumertest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.wxz.consumertest.Enum.ServicePathEnum;
import org.wxz.consumertest.service.HelloService;
import org.wxz.consumertest.service.HelloWordApi;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/hello")
public class HellowordConsumer {

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private HelloService helloService;

    @Autowired
    private HelloWordApi helloWordApi;

    @RequestMapping(value = "hello_word")
    public String hello(){
        return restTemplate.getForEntity(ServicePathEnum.PROVIDE_TEST_SERVICE.getValue(),String.class).getBody();
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hellofeign(){
        return helloService.hello()+"f";
    }

    @RequestMapping(value = "/hello_Api",method = RequestMethod.GET)
    public String helloApi(@RequestParam(value = "name")String name,
                           @RequestParam(value = "age")double age){
        return helloWordApi.hello(name,age);
    }

}
