package org.wxz.confserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wxz.confserver.service.impl.ResourceServiceImpl;
import org.wxz.confsysdomain.nconfsysconf.Resource;
import org.wxz.nconfsyscommon.resultVO.ConfResponse;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/29 2:36
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceServiceImpl resourceService;

    /**
     * 获取某一类文件列表
     * @param confId
     * @param type
     * @return
     */
    @GetMapping(value = "/find_by_ConIdAndType")
    public ConfResponse findByConIdAndType(@RequestParam(value = "confId",required = true)String confId,
                                           @RequestParam(value = "type",required = true)int type){
        List<Resource> resourceList= resourceService.findListByConfIdAndType(confId,type);
        return ConfResponse.success(resourceList);
    }


    @GetMapping(value ="search_by_OriginName" )
    public ConfResponse searchByOriginName(@RequestParam(value = "key")String key){
        List<Resource> resourceList=resourceService.findListByOriginNameLike(key);
        return ConfResponse.success(resourceList);
    }

}
