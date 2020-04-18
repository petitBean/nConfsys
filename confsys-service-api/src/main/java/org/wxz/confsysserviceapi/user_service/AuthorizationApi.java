package org.wxz.confsysserviceapi.user_service;

import org.apache.catalina.LifecycleState;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 提供授权的接口
 * @Author xingze Wang
 * @create 2020/4/14 1:43
 */




@FeignClient(name = "user-service")
public interface AuthorizationApi {

    /**
     *
     * @param userName 用户名
     * @return HashMap:里面有roles permissions 两个List
     */
    @GetMapping("/auth/getAuthInfoByUserName")
     HashMap<String, Set<String>> getAuthInfoByUserName(@RequestParam ("userName") String userName);

}
