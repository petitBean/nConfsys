package org.wxz.confsysuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.wxz.confsysdomain.nconfsysuser.Permission;

import java.util.List;
import java.util.Set;

/**
 * @Author xingze Wang
 * @create 2020/4/12 1:38
 */
public interface PermissionService {

    /**
     * 查询所有的权限
     * @return
     */
    List<Permission> findAllPermission();

    /**
     * 查找对应所有permissionId的permission
     * @param permissionIdSet
     * @return
     */
    List<Permission> findAllByPermissionIdIn(Set<String> permissionIdSet);

}
