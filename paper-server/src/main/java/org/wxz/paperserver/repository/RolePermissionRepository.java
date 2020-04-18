package org.wxz.paperserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.nconfsysuser.RolePermission;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/12 3:45
 */
@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission,Integer> {

    /**
     * 根据roleid查找所有的role_permission
     * @param roleIdList
     * @return
     */
    List<RolePermission> findAllByRoleIdIn(List<String> roleIdList);

}
