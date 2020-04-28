package org.wxz.confserver.service;

import org.wxz.confsysdomain.relation.ConferenceUer;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/25 1:21
 */
public interface ConferenceUserService {

    ConferenceUer findByConfIdAndUserName(String confId, String userName);

    ConferenceUer findByConferenceUserName(String conferenceUserName);

    ConferenceUer saveOne(ConferenceUer conferenceUer) throws Exception;

    List<ConferenceUer> findListByUserName(String userId);

}
