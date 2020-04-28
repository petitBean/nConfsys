package org.wxz.confserver.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confserver.vo.ApplyJoinConfTableVo;
import org.wxz.confserver.vo.HomePageVo;
import org.wxz.confsysdomain.nconfsysconf.Conference;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/4/24 1:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ConferenceServiceImplTest {

    @Autowired
    private ConferenceServiceImpl conferenceService;

    @Test
    void saveOne() {
    }

    @Test
    void findOneByConfId() {
    }

    @Test
    void createConf() {
    }

    @Test
    void findOnePageByStartTimeSort() {
       List<Conference> conferences=conferenceService.findOnePageByStartTimeSort(0,4);
        List<Conference> conferences2=conferenceService.findAllKeywordsLike("酷酷酷酷酷酷酷");
        List<Conference> conferences3=conferenceService.findAllConfTopicLike("酷酷酷酷酷酷酷");
       return;
    }

    @Test
    void findAllKeywordsLike() {

    }

    @Test
    void getHomeVo(){
        HomePageVo vo=conferenceService.getHomePageVo(0,5);
        return;
    }

    @Test
    void getApplyJoinTableVo(){
        List<ApplyJoinConfTableVo> voList=conferenceService.getApplyJoinTableVo("1587695588538656573");
        return;
    }
}