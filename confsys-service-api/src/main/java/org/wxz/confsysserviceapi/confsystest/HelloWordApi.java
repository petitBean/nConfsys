package org.wxz.confsysserviceapi.confsystest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "/hello")
public interface HelloWordApi {

    @RequestMapping(value = "/hello_api")
    public String hello(@RequestParam(value = "name")String name,
                        @RequestParam("age")double age);

}
