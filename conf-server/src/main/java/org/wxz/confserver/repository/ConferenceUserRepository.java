package org.wxz.confserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.relation.ConferenceUer;

/**
 * @Author xingze Wang
 * @create 2020/4/25 1:17
 */
@Repository
public interface ConferenceUserRepository extends JpaRepository<ConferenceUer,String> {

    ConferenceUer findByConfIdAndUserId(String confId,String userId);

    ConferenceUer findByConferenceUserId(String conferenceUserId);

}
