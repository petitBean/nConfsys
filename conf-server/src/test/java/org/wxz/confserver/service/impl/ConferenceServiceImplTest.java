package org.wxz.confserver.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confserver.vo.*;
import org.wxz.confsysdomain.nconfsysconf.Conference;
import org.wxz.confsysdomain.paper.Solicite;

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


    @Autowired
    private ApplicationServiceImpl applicationService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PaperServiceImpl paperService;

    @Autowired
    private SoliciteServiceIml soliciteServiceIml;


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
    void getUserJoinedConferenceList(){
        List<ApplyJoinConfTableVo> voList=conferenceService.getUserJoinedConference("13720131232");
        return;
    }

    @Test
    void getApplyJoinTableVo(){
        List<ApplyJoinConfTableVo> voList=conferenceService.getApplyJoinTableVo("1587695588538656573");
        return;
    }

    @Test
    void  getMyManagConfTableVo(){
        List<MyManagConfTableVo> result=conferenceService.getMyManagConfTableVo("13720131232");
        return;
    }

    @Test
    void getManagerVo() throws Exception{
        List<ManagerVo> re=conferenceService.getManagerVo("0001");
        return;
    }

    @Test
    void initPaperMAnnagCeter(){
        String confId="0001";
        List<UserVo> professorList=null;
        List<PaperVo> paperVoList=null;
        Solicite solicite=null;
        List<PaperViewResultVo> viewResultVos=null;

        try {
            professorList=userService.getProfessorList(confId);
        }catch (Exception e){
            //log.error("初始化会议管理中心-错误：e={}",e.getMessage()+e.getStackTrace());
        }

        try {
            paperVoList=paperService.getPaperVolistByConfid(confId);
        }catch (Exception e){
           // log.error("初始化会议管理中心-paperVoList错误：e={}",e.getMessage()+e.getStackTrace());
        }
        try {
            solicite=soliciteServiceIml.findOneByConfId(confId);
        }catch (Exception e){
           // log.error("初始化会议管理中心-solicite错误：e={}",e.getMessage()+e.getStackTrace());
        }
        try {
            viewResultVos=paperService.getPaperViewRsultList(confId);
        }catch (Exception e){
            //log.error("初始化会议管理中心-viewResultVos=错误：e={}",e.getMessage()+e.getStackTrace());
        }
        PaperManagCenterVo paperManagCenterVo=new PaperManagCenterVo();
        paperManagCenterVo.setPaperVoList(paperVoList);
        paperManagCenterVo.setProfessorList(professorList);
        paperManagCenterVo.setSolicite(solicite);
        paperManagCenterVo.setViewResultVoList(viewResultVos);
        //log.info("初始化会议管理中心result={}",paperManagCenterVo);
        return;
    }
}