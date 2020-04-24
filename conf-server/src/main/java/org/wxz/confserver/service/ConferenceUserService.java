package org.wxz.confserver.service;

import org.wxz.confsysdomain.relation.ConferenceUer;

/**
 * @Author xingze Wang
 * @create 2020/4/25 1:21
 */
public interface ConferenceUserService {

    ConferenceUer findByConfIdAndUserId(String confId, String userId);

    ConferenceUer findByConferenceUserId(String conferenceUserId);

    ConferenceUer saveOne(ConferenceUer conferenceUer) throws Exception;

}
