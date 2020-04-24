package org.wxz.confserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wxz.confserver.from.CreateConfFrom;
import org.wxz.confserver.service.impl.ConferenceDtailServiceimpl;
import org.wxz.confserver.service.impl.ConferenceServiceImpl;
import org.wxz.confserver.vo.DetailPageVo;
import org.wxz.confserver.vo.HomeConfVo;
import org.wxz.confserver.vo.HomePageVo;
import org.wxz.confsysdomain.nconfsysconf.Conference;
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
        return ConfResponse.success(voList);
    }


}
