package org.wxz.confserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.wxz.confserver.from.CreateConfFrom;
import org.wxz.confserver.vo.HomeConfVo;
import org.wxz.confserver.vo.HomePageVo;
import org.wxz.confsysdomain.nconfsysconf.Conference;

import java.util.List;
import java.util.Set;

/**
 * @Author xingze Wang
 * @create 2020/4/22 20:30
 */
public interface ConferenceService {

    Conference saveOne (Conference conference) throws Exception;

    Conference findOneByConfId(String ConfId) ;

    Conference createConf(CreateConfFrom createConfFrom) throws Exception;

    List<Conference> findOnePageByStartTimeSort(int page,int pageSize);

    List<Conference> findAllKeywordsLike(String key);

    List<Conference> findAllConfTopicLike(String key);

    HomePageVo getHomePageVo(int page,int pageSize);

    List<Conference> findAllByFocusedSort(int page,int pageSize);

    List<HomeConfVo> getHomeConfVoPage(int page,int pageSize);

    int countAll();

    List<HomeConfVo> searcheConfByKeyOrTopic( String key);

}
