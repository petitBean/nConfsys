package org.wxz.confserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confserver.from.CreateConfDetailFrom;
import org.wxz.confserver.repository.ConferenceDetailRepository;
import org.wxz.confserver.service.ConferenceDetailService;
import org.wxz.confserver.vo.DetailPageVo;
import org.wxz.confsysdomain.nconfsysconf.Conference;
import org.wxz.confsysdomain.nconfsysconf.ConferenceDetail;
import org.wxz.nconfsyscommon.enums.ConfIsOnLineEnum;
import org.wxz.nconfsyscommon.enums.ConfStatusEnum;
import org.wxz.nconfsyscommon.utils.DateUtil;
import org.wxz.nconfsyscommon.utils.KeyUtil;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/22 20:33
 */
@Slf4j
@Service
public class ConferenceDtailServiceimpl implements ConferenceDetailService {

    @Autowired
    private ConferenceDetailRepository detailRepository;

    @Autowired
    private ConferenceServiceImpl conferenceService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ConferenceDetail saveOne(ConferenceDetail detail) throws Exception {
        return detailRepository.save(detail);
    }

    @Override
    public ConferenceDetail findOneByDetailId(String detailId) {
        return detailRepository.findByConfDetailId(detailId);
    }

    @Override
    public List<ConferenceDetail> findAllByConfDetailIdIn(List<String> idList) {
        return detailRepository.findAllByConfDetailIdIn(idList);
    }

    @Override
    public DetailPageVo getConfDetailPageVo(String confId) {
        Conference conference=conferenceService.findOneByConfId(confId);
        if (conference==null){
            log.info("查询会议-结果为空：confId={}",confId);
            return null;
        }
        ConferenceDetail  detail=detailRepository.findByConfDetailId(conference.getConfDetailId());
        if (detail==null){
            log.info("查询会议-结果为空：detailId={}",conference.getConfDetailId());
            return null;
        }
        DetailPageVo detailPageVo=new DetailPageVo();
        BeanUtils.copyProperties(conference,detailPageVo);
        BeanUtils.copyProperties(detail,detailPageVo);
        //状态
        ConfStatusEnum statusEnum=ConfStatusEnum.getByCode(conference.getStatus());
        ConfIsOnLineEnum isOnLineEnum=ConfIsOnLineEnum.getByCode(conference.getIsOnline());
        if (statusEnum==null){
            log.error("获取会议详情VO-异常-状态转换错误：code={}",conference.getStatus());
        }
        else {
            detailPageVo.setStatusStr(statusEnum.getMessage());
        }
        if (isOnLineEnum==null){
            log.error("获取会议详情VO-异常-在线状态转换错误：code={}",conference.getIsOnline());
        }
        else {
            detailPageVo.setIsOnlineStr(isOnLineEnum.getMessage());
        }
        //时间-》字符串
        try{
            detailPageVo.setStartTimeStr(DateUtil.dateMinuteToStr(conference.getStartTime()));
            detailPageVo.setEndTimeStr(DateUtil.dateMinuteToStr(conference.getEndTime()));
            detailPageVo.setCreateTimeStr(DateUtil.dateMinuteToStr(conference.getCreateTime()));
            detailPageVo.setPaperCollectionEndStr(DateUtil.dateMinuteToStr(detail.getPaperCollectionEnd()));
            detailPageVo.setPaperCollectionStartStr(DateUtil.dateMinuteToStr(detail.getPaperCollectionStart()));
            detailPageVo.setPayStartTimeStr(DateUtil.dateMinuteToStr(detail.getPayStartTime()));
            detailPageVo.setPayEndTimeStr(DateUtil.dateMinuteToStr(detail.getPayEndTime()));
        }
        catch (Exception e){
            log.error("获取会议详情VO-异常-时间转换错误：e={},causeby={}",e.getStackTrace(),e.getCause());
        }
        return detailPageVo;
    }


    /**
     * 创建会议详情
     * @param detailFrom
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public ConferenceDetail createOneDetail(CreateConfDetailFrom detailFrom) throws Exception {
        if (detailFrom==null){
            return null;
        }
        ConferenceDetail detail=new ConferenceDetail();
        //赋值
        BeanUtils.copyProperties(detailFrom,detail);
        //id
        detail.setConfDetailId(KeyUtil.getUniqueKey());
        //conf 中设置id
        Conference conference=conferenceService.findOneByConfId(detailFrom.getConfId());
        if(conference==null){
            return null;
        }
        conference.setConfDetailId(detail.getConfDetailId());
        //设置状态
        conference.setStatus(ConfStatusEnum.COMPLETED_DETAIL_STATUS.getCode());
        //保存
        if (detailFrom.getPaperCollectionDate().length==2){
            detail.setPaperCollectionStart(detailFrom.getPaperCollectionDate()[0]);
            detail.setPaperCollectionEnd(detailFrom.getPaperCollectionDate()[1]);
        }
        if (detailFrom.getPayDate().length==2){
            detail.setPayStartTime(detailFrom.getPayDate()[0]);
            detail.setPayEndTime(detailFrom.getPayDate()[1]);
        }
        conference.setConfIntroduce(detailFrom.getConfIntroduce());
        ConferenceDetail conferenceDetail=null;
        try {
            conferenceService.saveOne(conference);
            conferenceDetail= detailRepository.save(detail);
        }
        catch (Exception e){
            throw e;
        }
       return conferenceDetail;
    }

}
