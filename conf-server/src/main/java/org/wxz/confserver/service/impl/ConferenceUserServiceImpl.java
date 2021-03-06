package org.wxz.confserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confserver.repository.ConferenceUserRepository;
import org.wxz.confserver.service.ConferenceUserService;
import org.wxz.confsysdomain.relation.ConferenceUer;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/25 1:22
 */
@Service
public class ConferenceUserServiceImpl implements ConferenceUserService {

    @Autowired
    private ConferenceUserRepository conferenceUserRepository;

    @Override
    public ConferenceUer findByConfIdAndUserName(String confId, String userName) {
        return conferenceUserRepository.findByConfIdAndUserName(confId,userName);
    }

    @Override
    public ConferenceUer findByConferenceUserName(String conferenceUserName) {
        return conferenceUserRepository.findByConferenceUserId(conferenceUserName);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ConferenceUer saveOne(ConferenceUer conferenceUer) throws Exception {
        synchronized (conferenceUer.getUserName()+conferenceUer.getConfId()){
            ConferenceUer conferenceUer1=findByConfIdAndUserName(conferenceUer.getConfId(),conferenceUer.getUserName());
            if (conferenceUer1!=null){
                return null;
            }
            return conferenceUserRepository.save(conferenceUer);
        }
    }

    @Override
    public List<ConferenceUer> findListByUserName(String userName) {
        return conferenceUserRepository.findAllByUserName(userName);
    }

    @Override
    public List<ConferenceUer> findAllByConfId(String confId) {
        return conferenceUserRepository.findAllByConfId(confId);
    }

    @Override
    public List<ConferenceUer> findAllByConfIdAndRoleName(String conId, String roleName) {
        return conferenceUserRepository.findAllByConfIdAndRoleName(conId,roleName);
    }
}
