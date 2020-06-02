package org.wxz.confserver.service;

import org.wxz.confsysdomain.nconfsysconf.ConferenceTag;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/23 17:06
 */
public interface ConferenceTagService {

    ConferenceTag saveOne(ConferenceTag conferenceTag) throws Exception;

    List<ConferenceTag> findAllByTagIdIn(List<String> tagIdList);

    List<ConferenceTag> saveAll(List<ConferenceTag> conferenceTagList) throws Exception;

}
