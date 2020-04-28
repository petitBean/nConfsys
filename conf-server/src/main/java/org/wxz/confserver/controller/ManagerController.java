package org.wxz.confserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wxz.confserver.service.impl.ConferenceServiceImpl;
import org.wxz.confserver.service_api.RoleApiService;
import org.wxz.confserver.vo.ConfManagTableVo;
import org.wxz.confserver.vo.ConfManageCenterVo;
import org.wxz.confsysserviceapi.user_service.RoleApi;
import org.wxz.nconfsyscommon.resultVO.ConfResponse;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/26 22:56
 */
@RestController
@RequestMapping("/manage")
public class ManagerController {

    @Autowired
    private ConferenceServiceImpl conferenceService;

    @Resource
    private RoleApiService service;

    @GetMapping(value = "/get_manage_vo")
    public ConfResponse getConfManageCenterVo(@RequestParam (value = "username") String username){
        ConfManageCenterVo centerVo=conferenceService.getConfManageCenterVo(username);
          return ConfResponse.success(centerVo);
    }

    @GetMapping("/test")
    public String test(){
        return service.test();
    }

}
