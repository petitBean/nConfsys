package org.wxz.confserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.relation.ConferenceUer;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/25 1:17
 */
@Repository
public interface ConferenceUserRepository extends JpaRepository<ConferenceUer,String> {

    ConferenceUer findByConfIdAndUserName(String confId,String userName);

    ConferenceUer findByConferenceUserId(String conferenceUserId);

    List<ConferenceUer> findAllByUserName(String userName);


}
