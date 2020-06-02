package org.wxz.confserver.controller;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wxz.confserver.dto.HomeConfVoDto;
import org.wxz.confserver.from.CreateConfFrom;
import org.wxz.confserver.service.impl.*;
import org.wxz.confserver.vo.*;
import org.wxz.confsysdomain.nconfsysconf.Application;
import org.wxz.confsysdomain.nconfsysconf.Conference;
import org.wxz.confsysdomain.paper.Solicite;
import org.wxz.nconfsyscommon.enums.ApplicationStatusEnum;
import org.wxz.nconfsyscommon.resultVO.ConfResponse;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/22 21:51
 */
@Slf4j
@RestController
@RequestMapping("/conference")
public class ConferenceController {

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

    /**
     * 创建会议
     * @param createConfFrom
     * @return
     */
    @RequestMapping(value = "/createconf",method = RequestMethod.POST)
    public ConfResponse createConf(@RequestBody CreateConfFrom createConfFrom){
        Conference conference=null;
        try {
          conference= conferenceService.createConf(createConfFrom);
        }
        catch (Exception e){
            log.error("创建会议-失败：from={},Exception={}",createConfFrom,e.getStackTrace());
            return ConfResponse.error();
        }
        log.info("创建会议-成功：会议id={}",conference.getConfId());
        return ConfResponse.success(conference);
    }

    /**
     * 首页初始化
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/getHomePageVo")
    public ConfResponse getHomePageVo(@RequestParam (value = "page",required = false,defaultValue = "0") int page ,
                                    @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize ){
         HomePageVo vo=conferenceService.getHomePageVo(page,pageSize);
         if (vo==null){
             log.info("获取首页数据-失败-null: vo={},date={}", vo, new Date());
             return ConfResponse.fail("获取数据失败");
         }
         return ConfResponse.success(vo);
    }


    /**
     * 下一页
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getHomeConfVoPage")
    public ConfResponse getHomeConfVoPage(@RequestParam (value = "page",required = false,defaultValue = "0") int page ,
                                          @RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize){
        List<HomeConfVo> confVoList=conferenceService.getHomeConfVoPage(page,pageSize);
        if (confVoList==null){
            confVoList= conferenceService.getHomeConfVoPage(page-1,pageSize);
            log.info("获取下一页HomeConfVo-失败-无更多数据：page={},pageSize={}",page,pageSize);
            return ConfResponse.success(confVoList);
        }
        return ConfResponse.success(confVoList);
    }


    //通过关键字搜索会议
    @GetMapping("/searche_conf_bykey")
    public ConfResponse searcheConfByKeyOrTopic(@RequestParam(value = "key",required = true)String key){
        if (key==null){
            return ConfResponse.fail("输入搜索内容！");
        }
        List<HomeConfVo> voList=conferenceService.searcheConfByKeyOrTopic(key);
        if (voList==null){
            return ConfResponse.fail("暂无相关信息");
        }
        log.info("查询用户加入到会议-成功");
        return ConfResponse.success(voList);
    }

    /**
     * 申请介入会议时搜索
     * @param key
     * @return
     */
    @GetMapping(value = "/searche_by_IdOrNameLike")
    public ConfResponse searcheByIdOrNameLike(@RequestParam(value = "key",required = true) String key){
        if (key==null){
            return null;
        }
        List<ApplyJoinConfTableVo> voList=conferenceService.getApplyJoinTableVo(key);
        return ConfResponse.success(voList);
    }


    /**
     * 获取用户已经加入的会议视图列表
     * @param userNam
     * @return
     */
    @GetMapping(value = "/get_Joined_Conferencelist")
    public ConfResponse getUserJoinedConferenceList(@RequestParam(value = "userName",required = true) String userNam){
        log.info(userNam);
        List<ApplyJoinConfTableVo> result=conferenceService.getUserJoinedConference(userNam);
        return ConfResponse.success(result);
    }



    @GetMapping("/")
    public ConfResponse getMyManagConfTableVo(@RequestParam(value = "username",required = true)String userName){
        return null;
    }

    @GetMapping("/initpersonal")
    public ConfResponse initPersonal(@RequestParam(value = "username")String userName){
        if (userName==null){
            log.error("用户中心初始化-错误-用户名参数未空");
            return ConfResponse.fail("系统异常");
        }
        List<ApplyJoinConfTableVo> joinedConference=conferenceService.getUserJoinedConference(userName);
        List<MyManagConfTableVo> myManagConfTableVoList=conferenceService.getMyManagConfTableVo(userName);

        PersonalCenterVo personalCenterVo=new PersonalCenterVo();
        personalCenterVo.setJoinedConference(joinedConference);
        personalCenterVo.setMyManagConfTableVoList(myManagConfTableVoList);
        log.info("初始化用户中心-成功：result={},userName={}",personalCenterVo,userName);
        return ConfResponse.success(personalCenterVo);
    }


    @GetMapping("/initconfmanagecenter")
    public ConfResponse initConfmanageCenter(@RequestParam(value = "confId")String confId){
        if (confId==null){
            log.error("会议管理中心初始化-错误-会议id参数为空");
            return ConfResponse.fail("系统异常");
        }
        List<ApplicationManageVo> applicationManageVos=null;
        List<ApplicationManageVo> passedVo=null;
        List<ManagerVo> workerList=null;
        try {
            applicationManageVos=applicationService.getApplicationManageVo(confId);
            passedVo=applicationService.getApplicationManageVoByConfIdAndStatus(confId, ApplicationStatusEnum.APPLICATION_STATUS_PASSED);
            workerList=conferenceService.getManagerVo(confId);
        }catch (Exception e){
             log.error("会议管理中心初始化-错误：e={}",e.getMessage()+e.getCause());
        }
        //添加参会人员列表
        ConfManageCenterVo result=new ConfManageCenterVo();
        result.setApplicationManageVoList(applicationManageVos);
        result.setPassedVolist(passedVo);
        result.setWorkerList(workerList);
        log.info("会议管理中心初始化-成功，result={},confId={}",result,confId);

        return ConfResponse.success(result);
    }


    @GetMapping("/initpapermanagcenter")
    public ConfResponse initConfManagCenter(@RequestParam(value = "confId",required = true)String confId){
        if (confId==null){
            log.error("初始论文管理中心-失败-会议id空");
            return ConfResponse.fail("请求参数错误");
        }
        List<UserVo> professorList=null;
        List<PaperVo> paperVoList=null;
        Solicite solicite=null;
        List<PaperViewResultVo> viewResultVos=null;

        try {
            professorList=userService.getProfessorList(confId);
        }catch (Exception e){
            log.error("初始化论文管理中心-错误：e={}",e.getMessage()+e.getStackTrace());
        }

        try {
            paperVoList=paperService.getPaperVolistByConfid(confId);
        }catch (Exception e){
            log.error("初始化论文管理中心-paperVoList错误：e={}",e.getMessage()+e.getStackTrace());
        }
        try {
            solicite=soliciteServiceIml.findOneByConfId(confId);
        }catch (Exception e){
            log.error("初始化论文管理中心-solicite错误：e={}",e.getMessage()+e.getStackTrace());
        }
        try {
            viewResultVos=paperService.getPaperViewRsultList(confId);
        }catch (Exception e){
            log.error("初始化论文管理中心-viewResultVos=错误：e={}",e.getMessage()+e.getStackTrace());
        }
        PaperManagCenterVo paperManagCenterVo=new PaperManagCenterVo();
        paperManagCenterVo.setPaperVoList(paperVoList);
        paperManagCenterVo.setProfessorList(professorList);
        paperManagCenterVo.setSolicite(solicite);
        paperManagCenterVo.setViewResultVoList(viewResultVos);
        log.info("初始化会议管理中心result={}",paperManagCenterVo);
        return ConfResponse.success(paperManagCenterVo);

    }

    @GetMapping("/find_by_tag")
    public ConfResponse findPageByTagNameList(@RequestParam(value = "tagNameList",required = true)List<String> tagNameList,
                                              @RequestParam(value = "page",required = false,defaultValue = "0")int page,
                                              @RequestParam(value = "pageSize",required = false,defaultValue = "6")int pageSize){
        if (tagNameList==null||tagNameList.isEmpty()){
           log.info("标签列表空");
            return ConfResponse.fail();
        }
        if (page<0||pageSize<1){
            log.info("参数错误");
            return ConfResponse.fail();
        }
        ConfListVo confListVo=new ConfListVo();
        HomeConfVoDto dto =conferenceService.findPageByTagNameList(page,pageSize,tagNameList);
        List<HomeConfVo> homeConfVos=dto.getConfVoList();
        confListVo.setConferenceList(homeConfVos);
        confListVo.setCountAll(dto.getCount());
        log.info("用户根据标签查询会议-成功-vo={}",confListVo);
        return ConfResponse.success(confListVo);
    }
}



