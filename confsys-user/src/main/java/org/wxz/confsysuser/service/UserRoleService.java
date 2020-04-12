package org.wxz.confsysuser.service;

import org.wxz.confsysdomain.nconfsysuser.UserRole;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/12 2:28
 */
public interface UserRoleService {

    List<UserRole> findAllUserRoleByUserId(String userId);

}
