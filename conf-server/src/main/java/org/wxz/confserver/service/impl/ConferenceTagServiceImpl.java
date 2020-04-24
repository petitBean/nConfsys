package org.wxz.confserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confserver.repository.ConferenceTagRepository;
import org.wxz.confserver.service.ConferenceTagService;
import org.wxz.confsysdomain.nconfsysconf.ConferenceTag;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/23 17:09
 */
@Service
@Slf4j
public class ConferenceTagServiceImpl implements ConferenceTagService {

    @Autowired
    private ConferenceTagRepository conferenceTagRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ConferenceTag saveOne(ConferenceTag conferenceTag) throws Exception {
        return conferenceTagRepository.save(conferenceTag);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public List<ConferenceTag> saveAll(List<ConferenceTag> conferenceTagList) throws Exception {
        return conferenceTagRepository.saveAll(conferenceTagList);
    }
}
