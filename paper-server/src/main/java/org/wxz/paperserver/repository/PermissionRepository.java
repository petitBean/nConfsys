package org.wxz.paperserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.nconfsysuser.Permission;

import java.util.List;
import java.util.Set;

/**
 * @Author xingze Wang
 * @create 2020/4/12 1:32
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission,String> {
    /**
     * 查找所有权限数据
     * @return
     */
    List<Permission> findAll();

    /**
     * 查找对应所有permissionId的permission
     * @param permissionIdSet
     * @return
     */
    List<Permission> findAllByPermissionIdIn(Set<String> permissionIdSet);
}
