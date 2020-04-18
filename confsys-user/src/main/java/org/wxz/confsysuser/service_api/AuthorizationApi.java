package org.wxz.confsysuser.service_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wxz.confsysuser.service.AuthorizationApiService;
import org.wxz.confsysuser.service.serviceImp.AuthorizationApiServiceImpl;
import org.wxz.confsysuser.service.serviceImp.ShiroServiceImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author xingze Wang
 * @create 2020/4/14 2:01
 */
@RestController
@RequestMapping("/auth")
public class AuthorizationApi  {

    @Autowired
    private AuthorizationApiServiceImpl authorizationApiService;

    /**
     * 返回用户授权信息
     * @param userName 用户名
     * @return
     */
    @RequestMapping(value = "/getAuthInfoByUserName",method = RequestMethod.GET)
    public HashMap<String, Set<String>> getAuthInfoByUserName(String userName) {
        Set<String> roles=new HashSet<>(1);
        System.out.println("wwww------------------------wwwwwwwwww");
        roles.add("teacher");
        Set<String> permissions=new HashSet<>(1);
        permissions.add("teacher:delete");
       // HashMap<String,Set<String>> authInfoByUserName=authorizationApiService.getAuthInfoByUserName(userName);
        HashMap<String,Set<String>> authInfoByUserName=new HashMap<>(2);
        authInfoByUserName.put("roles",roles);
        authInfoByUserName.put("permissions",permissions);
       // System.out.println(authInfoByUserName);
        return authInfoByUserName;
    }

}
