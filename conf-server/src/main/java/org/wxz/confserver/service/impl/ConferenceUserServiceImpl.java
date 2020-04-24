package org.wxz.confserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confserver.repository.ConferenceUserRepository;
import org.wxz.confserver.service.ConferenceUserService;
import org.wxz.confsysdomain.relation.ConferenceUer;

import javax.transaction.Transactional;

/**
 * @Author xingze Wang
 * @create 2020/4/25 1:22
 */
@Service
public class ConferenceUserServiceImpl implements ConferenceUserService {

    @Autowired
    private ConferenceUserRepository conferenceUserRepository;

    @Override
    public ConferenceUer findByConfIdAndUserId(String confId, String userId) {
        return conferenceUserRepository.findByConfIdAndUserId(confId,userId);
    }

    @Override
    public ConferenceUer findByConferenceUserId(String conferenceUserId) {
        return conferenceUserRepository.findByConferenceUserId(conferenceUserId);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ConferenceUer saveOne(ConferenceUer conferenceUer) throws Exception {
        return conferenceUserRepository.save(conferenceUer);
    }
}
