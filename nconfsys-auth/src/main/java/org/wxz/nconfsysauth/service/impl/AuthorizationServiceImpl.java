package org.wxz.nconfsysauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.wxz.confsysserviceapi.user_service.AuthorizationApi;
import org.wxz.nconfsysauth.service.AuthorizationService;
import org.wxz.nconfsysauth.util.SpringUtil;

import java.util.HashMap;
import java.util.Set;

/**
 * @Author xingze Wang
 * @create 2020/4/14 18:06
 */
@Service
public class AuthorizationServiceImpl  {

    @Autowired
   private AuthorizationService authorizationService;
    //@Override

    public HashMap<String, Set<String>> getAuthInfoByUserName(String userName) {
        System.out.println("authservice------------------------------");
        //authorizationService= SpringUtil.getBean(AuthorizationService.class);
         return authorizationService.getAuthInfoByUserName(userName);
    }

}
