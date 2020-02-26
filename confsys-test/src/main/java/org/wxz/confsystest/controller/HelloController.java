package org.wxz.confsystest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @Autowired
    private EurekaDiscoveryClient eurekaDiscoveryClient;

    @RequestMapping(value = "/hello_word",method = RequestMethod.GET)
    public String hellWord(){
        return "hello word!";
    }

}
