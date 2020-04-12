package org.wxz.confsysuser.service;

import org.wxz.confsysdomain.nconfsysuser.Role;

import java.util.List;
import java.util.Set;

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

}
