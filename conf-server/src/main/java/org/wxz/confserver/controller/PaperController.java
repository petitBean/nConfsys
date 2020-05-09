package org.wxz.confserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wxz.confserver.from.CommentFrom;
import org.wxz.confserver.service.impl.PaperServiceImpl;
import org.wxz.confserver.service.impl.SoliciteServiceIml;
import org.wxz.confserver.vo.PaperViewVo;
import org.wxz.confsysdomain.paper.Paper;
import org.wxz.confsysdomain.paper.Solicite;
import org.wxz.nconfsyscommon.enums.PaperStatusEnum;
import org.wxz.nconfsyscommon.resultVO.ConfResponse;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/8 23:03
 */
@Slf4j
@RestController
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperServiceImpl paperService;

    @Autowired
    private SoliciteServiceIml soliciteServiceIml;

    @GetMapping("/init")
    public ConfResponse initPaperViewCenter(@RequestParam(value = "confId",required = true) String confId,
                                            @RequestParam(value = "userName",required = true)String userName){
        if (confId==null){
            log.error("初始化评阅中心-错误-参数空");
            return ConfResponse.fail("页面数据加载失败");
        }
        PaperViewVo viewVo=new PaperViewVo();
        List<Paper> waitViewList=null;
        try {
            waitViewList=paperService.findListByProfessorUserNameAndConfIdAndStatus(userName,confId, PaperStatusEnum.PAPER_STATUS_ENUM_NEW.getCode());
        }catch (Exception  e){
            log.error("1-kong");
        }
        Solicite solicite=null;
        try {
            solicite=soliciteServiceIml.findOneByConfId(confId);
        }catch (Exception  e){
           log.error("2-空");
        }
        List<Paper> viewedList=paperService.getViewdList(confId,userName);
        viewVo.setNoViewPaperList(waitViewList);
        viewVo.setSolicit(solicite);
        viewVo.setViewedPaperList(viewedList);
        log.info("初始化评阅中心-username={},vo={}",userName,viewVo);
        return ConfResponse.success(viewVo);

    }

    @PostMapping("/comment")
    public ConfResponse commentPaper(@RequestBody CommentFrom commentFrom){
        log.info("用户评阅：from={}",commentFrom);
        if (commentFrom==null){
            return ConfResponse.fail("请求错误");
        }
        try {
            paperService.commentPaper(commentFrom);
        }catch (Exception e){
            log.error("用户评阅论文-失败");
            return ConfResponse.fail("请求失败");
        }
        log.info("用户评阅论文-成功");
        return ConfResponse.success();
    }

}
