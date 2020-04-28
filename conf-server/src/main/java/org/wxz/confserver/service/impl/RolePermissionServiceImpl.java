package org.wxz.confserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confserver.repository.RolePermissionRepository;
import org.wxz.confserver.service.RolePermissionService;
import org.wxz.confsysdomain.nconfsysuser.RolePermission;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/12 3:48
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;


    /**
     * 根据roleid查找所有的role_permission
     * @param roleIdList
     * @return
     */
    @Override
    public List<RolePermission> findAllByRoleIdIn(List<String> roleIdList) {
        return rolePermissionRepository.findAllByRoleIdIn(roleIdList);
    }
}
