package org.wxz.confserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wxz.confserver.service.impl.ApplicationServiceImpl;
import org.wxz.confserver.vo.ApplicationVo;
import org.wxz.confsysdomain.nconfsysconf.Application;
import org.wxz.nconfsyscommon.exception.ConfException;
import org.wxz.nconfsyscommon.resultVO.ConfResponse;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/29 1:06
 */
@Slf4j
@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationServiceImpl applicationService;

    /**
     * 查询新的申请vo
     * @param confId
     * @return
     */
    public ConfResponse  getAllNewApplicationByConfId(@RequestParam(value = "confId",required = true)String confId){
        if (confId==null){
           log.error("查询申请列表-异常-参数为空：");
           return ConfResponse.fail("系统异常!");
        }
        List<ApplicationVo> voList=applicationService.getAllNewApplicationByConfId(confId);
        log.info("查询申请列表-成功！confId={}",confId );
        return ConfResponse.success(voList);
    }


    @PostMapping(value = "/create_application")
    public ConfResponse createApplication(@RequestParam(value = "userName",required = true)String userName,
                                          @RequestParam(value = "confId",required = true)String confId)  {
        if (userName==null|| confId==null){
            return ConfResponse.fail("系统异常！");
        }
        try {
            Application application=applicationService.createOne(userName,confId);

        }catch (Exception e){
            if (e instanceof ConfException){
                log.info("创建申请-失败-用户已经创建：userName={},confId={}",userName,confId);
                 return ConfResponse.fail("您已经申请过！");
            }
        }
        return ConfResponse.success("申请成功！等待审核！");
    }



}
