package org.wxz.confsysuser.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confsysdomain.nconfsysuser.Permission;
import org.wxz.confsysuser.repository.PermissionRepository;
import org.wxz.confsysuser.service.PermissionService;

import java.util.List;
import java.util.Set;

/**
 * @Author xingze Wang
 * @create 2020/4/12 1:41
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * 查找所有权限
     * @return
     */
    @Override
    public List<Permission> findAllPermission() {
        return permissionRepository.findAll();
    }

    /**
     * 查找对应所有permissionId的permission
     * @param permissionIdSet
     * @return
     */
    @Override
    public List<Permission> findAllByPermissionIdIn(Set<String> permissionIdSet) {
        return permissionRepository.findAllByPermissionIdIn(permissionIdSet);
    }


}
