package org.wxz.confserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wxz.confserver.from.CreateConfDetailFrom;
import org.wxz.confserver.service.impl.ConferenceDtailServiceimpl;
import org.wxz.confserver.vo.DetailPageVo;
import org.wxz.confsysdomain.nconfsysconf.ConferenceDetail;
import org.wxz.nconfsyscommon.resultVO.ConfResponse;

/**
 * @Author xingze Wang
 * @create 2020/4/23 17:34
 */
@Slf4j
@RestController
@RequestMapping("/conferencedetail")
public class ConferenceDetailController {

    @Autowired
    private ConferenceDtailServiceimpl detailServiceimpl;

    /**
     * 会议详情
     * @param confId
     * @return
     */
    @GetMapping(value = "/get_confdetailvo")
    public ConfResponse getConfDetaiVo(@RequestParam(value = "confId",required = true) String confId){
        DetailPageVo detailPageVo=detailServiceimpl.getConfDetailPageVo(confId);
        if (detailPageVo==null){
            return ConfResponse.fail("暂无相关数据！");
        }
        return ConfResponse.success(detailPageVo);
    }

    /**
     * 创建会议详情
     * @param detailFrom
     * @return
     */
    @RequestMapping(value = "/create_one_detail",method = RequestMethod.POST)
    public ConfResponse createOneDetail(@RequestBody CreateConfDetailFrom detailFrom){
        if (detailFrom==null){
            log.info("创建会议详情-失败-参数空！");
            return ConfResponse.fail("参数不可为空！");
        }
        ConferenceDetail detail=null;
        try {
            detail=detailServiceimpl.createOneDetail(detailFrom);
        }
        catch (Exception e){
            log.error("创建会议-失败：from={},e={}",detailFrom,e.getStackTrace());
            return ConfResponse.fail("失败！系统异常");
        }
        return ConfResponse.success(detail);
    }


}
