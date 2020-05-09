package org.wxz.confserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wxz.confserver.from.ApplicationFrom;
import org.wxz.confserver.service.impl.ApplicationServiceImpl;
import org.wxz.confserver.vo.ApplicationManageVo;
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
    public ConfResponse createApplication(@RequestBody ApplicationFrom applicationFrom)  {
        if (applicationFrom==null){
            return ConfResponse.fail("系统异常！");
        }
        try {
            Application application=applicationService.createOne(applicationFrom);
        }catch (Exception e){
            log.info("创建申请-失败-异常：applicationFrom={}",applicationFrom);
            return ConfResponse.fail(e.getMessage());
        }
        return ConfResponse.success("申请成功！等待审核！");
    }


    @GetMapping("/getApplicationManageVo")
    public ConfResponse getApplicationManageVo(@RequestParam(value = "confId")String confId){
        if (confId==null){
            log.error("用户查询申请管理vo-错误-参数confID为空");
            return ConfResponse.fail("系统错误！");
        }
        List<ApplicationManageVo> voList=null;
        try {
           voList=applicationService.getApplicationManageVo(confId);
        }
        catch (Exception e){
            log.error("用户查询申请管理vo-错误:e={}",e.getMessage()+'\n'+e.getStackTrace());
            return ConfResponse.fail("系统异常！");
        }
        return ConfResponse.success(voList);
    }

    @PostMapping("/refuseApply")
    public ConfResponse refuseApply(@RequestParam(value = "applicationId",required = true)String applicationId){
        if (applicationId==null){
            log.error("拒绝用户会议申请-错误-applicationId==null！");
            return ConfResponse.fail("失败");
        }
        try {
            applicationService.refuseApply(applicationId);
        }
        catch (Exception e){
            log.error("拒绝用户会议申请-错误:e={}",e.getMessage()+"\n"+e.getCause());
            return ConfResponse.fail("失败");
        }
        log.error("拒绝用户会议申请-成功-applicationId={}",applicationId);
        return ConfResponse.success();
    }

    @PostMapping("/passApply")
    public ConfResponse passApply(@RequestParam(value = "applicationId",required = true)String applicationId){
        if (applicationId==null){
            log.error("通过用户会议申请-错误-applicationId==null！");
            return ConfResponse.fail("失败");
        }
        try {
            applicationService.passApply(applicationId);
        }
        catch (Exception e){
            log.error("通过用户会议申请-错误:e={}",e.getMessage()+"\n"+e.getCause());
            return ConfResponse.fail("失败");
        }
        log.error("通过用户会议申请-成功-applicationId={}",applicationId);
        return ConfResponse.success();
    }



}
