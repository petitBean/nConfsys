package org.wxz.confsysuser.service;

import java.util.Set;

/**
 * @Author xingze Wang
 * @create 2020/4/12 2:13
 */
public interface ShiroService {

    Set<String> getRolesByUserName(String userName);

    Set<String> getPermissionsByUserName(String userName);

    String getPasswordByUserName(String userName);

}
