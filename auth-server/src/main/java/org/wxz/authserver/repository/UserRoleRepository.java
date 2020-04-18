package org.wxz.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.nconfsysuser.UserRole;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/12 2:24
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {

    /**
     * 通过用户Id查询所有的用户角色关系
     * @return
     */
    List<UserRole> findAllByUserId(String userId);

}
