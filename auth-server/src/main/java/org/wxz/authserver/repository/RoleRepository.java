package org.wxz.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.nconfsysuser.Role;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/12 1:20
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    /**
     * 查找所有的角色
     * @return
     */
    List<Role> findAll();

    /**
     * 查找对应所有roleId的role对象
     * @param roleIdList
     * @return
     */
    List<Role> findAllByRoleIdIn(List<String> roleIdList);


    List<Role> findAllByRoleNameIn(List<String> roleNameList);


    /**
     *
     * @param roleName
     * @return
     */
    Role findByRoleName(String roleName);

}
