package org.wxz.nconfsysauth.fallback;

import org.springframework.stereotype.Component;
import org.wxz.nconfsysauth.service.AuthorizationService;

import java.util.HashMap;
import java.util.Set;

/**
 * @Author xingze Wang
 * @create 2020/4/14 2:43
 */
@Component
public class AuthorizationFallback{

    //@Override
    public HashMap<String, Set<String>> getAuthInfoByUserName(String userName) {
        return null;
    }
}
