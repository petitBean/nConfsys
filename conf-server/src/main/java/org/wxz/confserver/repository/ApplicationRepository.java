package org.wxz.confserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.nconfsysconf.Application;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/29 0:32
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application,String> {

    Application findByUserNameAndConfId(String userName,String confId);

    List<Application> findAllByConfId(String confId);

    List<Application> findAllByUserName(String userName);

}
