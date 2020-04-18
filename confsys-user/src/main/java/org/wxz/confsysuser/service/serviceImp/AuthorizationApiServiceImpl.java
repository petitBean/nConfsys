package org.wxz.confsysuser.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confsysuser.service.AuthorizationApiService;

import java.util.HashMap;
import java.util.Set;

/**
 * @Author xingze Wang
 * @create 2020/4/14 2:11
 */
@Service
public class AuthorizationApiServiceImpl implements AuthorizationApiService {

   @Autowired
   private ShiroServiceImpl shiroService;

    @Override
    public HashMap<String, Set<String>> getAuthInfoByUserName(String userName) {
        if (userName==null){
            return null;
        }
        HashMap<String, Set<String>> result=new HashMap<>(2);
        Set<String> roles=shiroService.getRolesByUserName(userName);
        Set<String> permissions=shiroService.getPermissionsByUserName(userName);
        return null;
    }

}
