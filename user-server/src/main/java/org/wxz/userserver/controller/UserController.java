package org.wxz.userserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xingze Wang
 * @create 2020/4/19 19:57
 */
@RestController
public class UserController {

    @RequestMapping(value = "/userLogin")
    public String userLogin(){
        return "登录";
    }

    @GetMapping(value = "/test")
    public String test(){
        return "user test";
    }

}
