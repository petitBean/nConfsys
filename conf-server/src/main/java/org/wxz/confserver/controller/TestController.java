package org.wxz.confserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxz.confsysdomain.nconfsysuser.UserDto;

/**
 * @Author xingze Wang
 * @create 2020/4/18 1:29
 */
@RestController
public class TestController {

    @GetMapping(value = "/r1")
    //@PreAuthorize("hasAnyAuthority('p1','p2')")
    public String r1(){
        UserDto userDto= (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDto.getUserName()+"访问资源1";
    }

    @GetMapping(value = "/teacher")
    //@PreAuthorize("hasAnyAuthority('p1','p2')")
    public String r2(){
        return "访问资源2";
    }

}
