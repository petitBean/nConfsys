package org.wxz.nconfsysauth.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wxz.confsysserviceapi.user_service.AuthorizationApi;
import org.wxz.nconfsysauth.fallback.AuthorizationFallback;

import java.util.HashMap;
import java.util.Set;

/**
 * @Author xingze Wang
 * @create 2020/4/14 2:19
 */
@FeignClient(value = "user-service")
public interface AuthorizationService  {

    /**
     *
     * @param userName 用户名
     * @return HashMap:里面有roles permissions 两个List
     */
    @RequestMapping(value = "/auth/getAuthInfoByUserName",method = RequestMethod.GET)
    HashMap<String, Set<String>> getAuthInfoByUserName(@RequestParam ("userName") String userName);

}
