package org.wxz.authserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wxz.authserver.from.UserFrom;
import org.wxz.authserver.service.impl.UserServiceImpl;
import org.wxz.confsysdomain.nconfsysuser.User;
import org.wxz.confsysdomain.nconfsysuser.UserDto;
import com.alibaba.fastjson.JSON;
import org.wxz.nconfsyscommon.resultVO.ConfResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xingze Wang
 * @create 2020/4/20 23:51
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户注册
     *
     * @return
     */
    @PostMapping(value = "/register")
    public ConfResponse r1(@RequestParam(value = "userName",required = true) String userName,
                           @RequestParam(value = "password",required = true) String password) {
        UserFrom userFrom=new UserFrom(userName,password);
        User user=null;
        try {
            user=userService.findOneByUserName(userName);
        }catch (Exception e){
            log.error("用户注册-注册失败： userFrom={}",userFrom);
            return ConfResponse.fail("用户名已经存在");
        }

        if(user!=null){
            log.error("用户注册-注册失败： userFrom={}",userFrom);
            return ConfResponse.fail("用户名已经存在");
        }
        try {
         user  =userService.userRegister(userFrom);
        }
        catch (Exception e){
            log.error("用户注册-系统异常： userFrom={}",userFrom);
            return ConfResponse.fail();
        }
        log.info("用户注册-成功：username={}",userFrom.getUserName());
        return ConfResponse.success(user);
    }



    @GetMapping(value = "/teacher/r2")
    @PreAuthorize("hasAnyAuthority('p1','p2')")
    public String r2(){
        return "访问资源2";
    }



}
