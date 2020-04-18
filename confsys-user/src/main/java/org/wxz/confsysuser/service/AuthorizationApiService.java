package org.wxz.confsysuser.service;

import java.util.HashMap;
import java.util.Set;

/**
 * @Author xingze Wang
 * @create 2020/4/14 2:09
 */
public interface AuthorizationApiService {

    HashMap<String, Set<String>> getAuthInfoByUserName(String userName);
}
