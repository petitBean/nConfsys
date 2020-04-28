package org.wxz.confserver.service_api.fallback;

import org.springframework.stereotype.Component;
import org.wxz.confserver.service_api.RoleApiService;
import org.wxz.confsysdomain.nconfsysuser.Role;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/27 0:02
 */
@Component
public class RoleApiServiceFallback  {

   // @Override
    public List<Role> findListByNameIn(List<String> roleNameList) {
        System.out.println("ccccccccccc");
        return null;
    }

}
