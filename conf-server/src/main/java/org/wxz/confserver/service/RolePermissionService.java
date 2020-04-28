package org.wxz.confserver.service;

import org.wxz.confsysdomain.nconfsysuser.RolePermission;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/12 3:48
 */
public interface RolePermissionService {

    List<RolePermission> findAllByRoleIdIn(List<String> roleIdList);

}
