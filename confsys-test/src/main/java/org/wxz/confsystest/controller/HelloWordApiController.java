package org.wxz.confsystest.controller;

import org.springframework.web.bind.annotation.RestController;
import org.wxz.confsysserviceapi.confsystest.HelloWordApi;

@RestController
public class HelloWordApiController implements HelloWordApi {

    @Override
    public String hello(String name, double age) {
        return "Hello API"+name+String.valueOf(age);
    }

}
