package org.wxz.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.wxz.confsysserviceapi.user_service.AuthorizationApi;

/**
 * @Author xingze Wang
 * @create 2020/4/14 21:29
 */
@FeignClient(value = "user-service")
public interface TestFeign {
}
