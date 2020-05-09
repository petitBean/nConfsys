package org.wxz.confserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.nconfsysuser.User;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/16 15:10
 */
@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByUserName(String userName);

    User findByUserId(String userId);

    List<User> findAllByUserNameIn(List<String> userNameList);

}
