package org.wxz.confserver.service_api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.wxz.confserver.service_api.fallback.RoleApiServiceFallback;
import org.wxz.confsysdomain.nconfsysuser.Role;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/26 23:56
 */
@FeignClient(value = "auth-service"/*fallback = RoleApiServiceFallback.class*/)
public interface RoleApiService {

    @GetMapping(value = "/auth-service/role_api/find_list_by_rolename")
    List<Role> findListByNameIn(@RequestBody List<String> roleNameList);

    @GetMapping(value = "/role_api/test")
    String test();

}
