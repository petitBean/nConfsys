package org.wxz.demo.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {


    @GetMapping(value = "/hello1")
    public String hello(){
        System.out.println("xxx");
        return "ccc";
    }

    //@RequiresRoles(value ={"teacher","user"},logical = Logical.OR)
   // @RequiresPermissions("teacher:delete")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
     public String testAuth(){
        return "teacher:delete";
     }

}
