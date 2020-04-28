package org.wxz.authserver.service;

import org.wxz.confsysdomain.nconfsysuser.Role;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/12 1:44
 */
public interface RoleService {

    List<Role> findAllRole();

    /**
     * 查找对应所有roleId的role对象
     * @param roleIdList
     * @return
     */
    List<Role> findAllByRoleIdIn(List<String> roleIdList);

    /**
     *
     * @param roleName
     * @return
     */
    Role findByRoleName(String roleName);

    List<Role> findListByRoleNameIn(List<String> roleNameList);

}
