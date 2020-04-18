package org.wxz.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.wxz.confsysserviceapi.user_service.AuthorizationApi;
import org.wxz.demo.service.TestFeign;
import org.wxz.demo.util.SpringUtil;
//import org.wxz.nconfsysauth.service.AuthorizationService;
//import org.wxz.nconfsysauth.service.impl.AuthorizationServiceImpl;

import java.util.HashMap;
import java.util.Set;


/**
 * @Author xingze Wang
 * @create 2020/4/11 22:59
 */
@RestController
@RequestMapping
public class HomeIndexController {


    //@Autowired
    //private AuthorizationService authorization;

  // @Autowired
   // private TestFeign testFeign;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public String defaultLogin(){
        return "首页";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String doLogin(@RequestParam ("username") String userName,
                          @RequestParam("password") String password){
        if(userName==null || password==null){
            return null;
        }
        //生成Subject
        Subject subject= SecurityUtils.getSubject();
        //生成令牌
        UsernamePasswordToken token=new UsernamePasswordToken(userName,password);
        subject.login(token);
        if (!subject.isAuthenticated()){
            token.clear();
            return "登录失败！";
        }
        return "OK！登录成功！";
    }


    @GetMapping(value = "/hello")
    public String hello(){
        return "helo";
    }

    @GetMapping("/notice")
    public String testNotice(){
        System.out.println("-----------NNNN-------------NNNN-----------nnnnnnn");
        //System.out.println(authorizationService.getAuthInfoByUserName("wxz").toString());
        return "xx";
    }

}
