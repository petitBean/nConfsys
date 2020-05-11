package org.wxz.confserver.service.impl;

import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confserver.from.PayCategoryFrom;
import org.wxz.confserver.repository.PayCategoryRepository;
import org.wxz.confserver.service.PayCategoryService;
import org.wxz.confsysdomain.nconfsysconf.Conference;
import org.wxz.confsysdomain.nconfsysconf.PayCategory;
import org.wxz.nconfsyscommon.enums.ConfStatusEnum;
import org.wxz.nconfsyscommon.enums.PayCategoryStatusEnum;
import org.wxz.nconfsyscommon.exception.ConfException;
import org.wxz.nconfsyscommon.utils.KeyUtil;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/5/9 6:24
 */
@Service
@Slf4j
public class PayCategoryServiceImpl implements PayCategoryService {

    @Autowired
    private PayCategoryRepository payCategoryRepository;

    @Autowired
    private ConferenceServiceImpl conferenceService;

    @Override
    public PayCategory saveOne(PayCategory payCategory) throws Exception {
        return payCategoryRepository.save(payCategory);
    }

    @Override
    public PayCategory findOneByConfId(String confId) throws Exception {
        return payCategoryRepository.findByConfId(confId);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public PayCategory createOne(PayCategoryFrom from) throws Exception {
        if (from.getConfId()==null){
            log.error("创建catrgory-错误-参数空");
            throw  new ConfException("失败-错误的请求！");
        }
        PayCategory payCategory=null;
        try {
            payCategory=payCategoryRepository.findByConfId(from.getConfId());
        }catch (Exception e){
            log.error("创建category-失败-查询出错");
            throw  new ConfException("系统异常");
        }
        if (payCategory!=null){
            BeanUtils.copyProperties(from,payCategory);
        }else {
            payCategory=new PayCategory();
            BeanUtils.copyProperties(from,payCategory);
            payCategory.setStatus(PayCategoryStatusEnum.CATEGORY_STATUS_ENUM_PAYING.getCode());
            payCategory.setId(KeyUtil.getUniqueKey());
            payCategory.setCreateTime(new Date());
            if (from.getDates().length==2){
                payCategory.setStartDate(from.getDates()[0]);
                payCategory.setEndDate(from.getDates()[1]);
            }
        }

        Conference conference=null;
        try {
            conference=conferenceService.findOneByConfId(from.getConfId());
        }catch (Exception e){
            log.error("创建category-错误-查询会议失败");
            throw  new ConfException("会议信息不存在！");
        }
        conference.setStatus(ConfStatusEnum.PAYING_CONF_STATUS.getCode());
        try {
            conferenceService.saveOne(conference);
            payCategory=payCategoryRepository.save(payCategory);
        }catch (Exception e){
            log.error("创建category-错误-存储失败");
            throw  new ConfException("请求失败");
        }
        log.info("创建category-成功：re={}",payCategory);
        return payCategory;
    }
}
