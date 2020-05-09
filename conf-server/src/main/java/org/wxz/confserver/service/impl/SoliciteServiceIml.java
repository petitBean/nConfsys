package org.wxz.confserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confserver.from.ViewDemandFrom;
import org.wxz.confserver.repository.SoliciteRepository;
import org.wxz.confserver.service.SoliciteService;
import org.wxz.confsysdomain.nconfsysconf.Conference;
import org.wxz.confsysdomain.nconfsysconf.ConferenceDetail;
import org.wxz.confsysdomain.paper.Solicite;
import org.wxz.nconfsyscommon.enums.ConfStatusEnum;
import org.wxz.nconfsyscommon.enums.SoliciteStatusEnum;
import org.wxz.nconfsyscommon.exception.ConfException;
import org.wxz.nconfsyscommon.utils.KeyUtil;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/5/6 21:58
 */
@Service
@Slf4j
public class SoliciteServiceIml implements SoliciteService {

    @Autowired
    private SoliciteRepository soliciteRepository;

    @Autowired
    private ConferenceServiceImpl conferenceService;

    @Autowired
    private ConferenceDtailServiceimpl dtailServiceimpl;

    @Autowired
    private PaperServiceImpl paperService;

    @Override
    public Solicite findOneByConfId(String confId) throws Exception {
        return soliciteRepository.findByConfId(confId);
    }

    /**
     * 创建solicite
     * @param confId
     * @param demand
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Solicite createOne(String confId, String demand, Date startDate, Date endDate) throws Exception {
        if (confId==null ||demand==null ||startDate==null||endDate==null){
            log.error("创建solicite-错误-参数为空");
            throw  new ConfException("失败！参数为空！");
        }
        Conference conference=conferenceService.findOneByConfId(confId);
        if (conference==null){
            log.error("创建solicite-错误-会议信息不存在");
            throw  new ConfException("失败！");
        }
        conference.setStatus(ConfStatusEnum.PAPER_COLLECTINT_STATUS.getCode());
        ConferenceDetail detail=dtailServiceimpl.findOneByDetailId(conference.getConfDetailId());
        if (detail==null){
            log.error("创建solicite-错误-会议详情不存在");
            throw  new ConfException("失败！！");
        }
        detail.setPaperDemanded(demand);
        Solicite solicite =new Solicite();
        solicite.setConfId(confId);
        solicite.setDemand(demand);
        solicite.setSoliciteId(KeyUtil.getUniqueKey());
        solicite.setStartDate(startDate);
        solicite.setEndDate(endDate);
        solicite.setStatus(SoliciteStatusEnum.SOLICITE_STATUS_ENUM_CREATED.getCode());
        conferenceService.saveOne(conference);
        dtailServiceimpl.saveOne(detail);
        return soliciteRepository.save(solicite);
    }


    @Override
    public Solicite addViewDemand(ViewDemandFrom from) throws Exception {
        log.info(from.toString());
        if (from.getConfId()==null || from.getViewDemand()==null){
            log.error("用户添加论文评阅要求-失败-参数为空");
            throw new ConfException("错误的请求");
        }
        Solicite solicite=null;
        try {
            solicite=findOneByConfId(from.getConfId());
        }catch (Exception e){
            log.error("用户添加论文评阅要求-错误-solicited查询错误");
            throw new ConfException("请求失败");
        }
        if (solicite==null){
            log.error("用户添加论文评阅要求-错误-solicited-null");
            throw new ConfException("请求失败");
        }
        solicite.setViewDemand(from.getViewDemand());
        Solicite result=null;
        Conference conference=conferenceService.findOneByConfId(from.getConfId());
        if (conference==null){
            log.error("创建solicite-错误-会议信息不存在");
            throw  new ConfException("失败！");
        }
        conference.setStatus(ConfStatusEnum.HOLDING_STATUS.getCode());
        solicite.setStatus(SoliciteStatusEnum.SOLICITE_STATUS_ENUM_COMMENT.getCode());
        try {
            conferenceService.saveOne(conference);
            result=soliciteRepository.save(solicite);
        }catch (Exception e){
            log.error("用户添加论文评阅要求-错误-solicited-存储失败");
            throw new ConfException("请求失败");
        }
        //分配专家
        paperService.distributeProfessor(from.getConfId());
        log.info("添加评阅要求-成功");
        return result;
    }


}
