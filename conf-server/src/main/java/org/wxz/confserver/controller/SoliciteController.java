package org.wxz.confserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wxz.confserver.from.SolicitStartFrom;
import org.wxz.confserver.from.ViewDemandFrom;
import org.wxz.confserver.repository.ConferenceRepository;
import org.wxz.confserver.service.impl.SoliciteServiceIml;
import org.wxz.confsysdomain.paper.Solicite;
import org.wxz.nconfsyscommon.resultVO.ConfResponse;

import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/5/6 22:23
 */
@RestController
@Slf4j
@RequestMapping("/solicite")
public class SoliciteController {

    @Autowired
    private SoliciteServiceIml soliciteServiceIml;

    @PostMapping("/createOneSolicite")
    public ConfResponse createOneSolicite(@RequestBody SolicitStartFrom solicitStartFrom){
        if (solicitStartFrom.getDates().length!=2){
            log.error("用户创建solicite-错误-参数为空");
            return ConfResponse.fail("失败！参数错误");
        }
        try {
            Solicite solicite=soliciteServiceIml.createOne(solicitStartFrom.getConfId(),solicitStartFrom.getDemand(),solicitStartFrom.getDates()[0],solicitStartFrom.getDates()[1]);
        }catch (Exception e){
            log.error("用户创建solicite-错误：e={}",e.getMessage()+e.getCause());
            return ConfResponse.fail("系统异常！");
        }
        log.info("用户创建solicite-成功");
        return ConfResponse.success();
    }

    /**
     *
     * 添加论文评阅要求
     * @param viewDemandFrom
     * @return
     */
    @PostMapping("/addViewDemand")
    public ConfResponse addViewDemand(@RequestBody ViewDemandFrom  viewDemandFrom){

        if (viewDemandFrom==null){
            log.error("用户添加评阅要求-错误-参数为空");
            return ConfResponse.fail("错误的请求");
        }
        try {
            soliciteServiceIml.addViewDemand(viewDemandFrom);
        }catch (Exception e){
            log.error("用户添加论文评阅要求-错误:e={}",e.getMessage()+e.getCause());
            return ConfResponse.fail(e.getMessage());
        }
        log.error("用户添加评阅要求-成功-from={}",viewDemandFrom);
        return ConfResponse.success();
    }

}
