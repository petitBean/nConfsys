package org.wxz.confserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confserver.repository.ApplicationRepository;
import org.wxz.confserver.service.ApplicationService;
import org.wxz.confserver.vo.ApplicationVo;
import org.wxz.confsysdomain.nconfsysconf.Application;
import org.wxz.nconfsyscommon.enums.ApplicationStatusEnum;
import org.wxz.nconfsyscommon.exception.ConfException;
import org.wxz.nconfsyscommon.utils.DateUtil;
import org.wxz.nconfsyscommon.utils.KeyUtil;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/29 0:37
 */
@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public Application findOneByUserNameAndConfId(String userName, String confId) {
        return applicationRepository.findByUserNameAndConfId(userName,confId);
    }

    @Override
    public List<Application> findListByConfId(String confId) {
        return applicationRepository.findAllByConfId(confId);
    }

    @Override
    public List<Application> findListByUserName(String userName) {
        return applicationRepository.findAllByUserName(userName);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Application saveOne(Application application) throws Exception {
        return applicationRepository.save(application);
    }

    @Override
    public Application createOne(String userName, String confId) throws Exception {
        Application applicationFoubded=applicationRepository.findByUserNameAndConfId(userName,confId);
        if (applicationFoubded!=null){
          throw new ConfException("该用户已经申请!");
        }
        Application application=new Application();
        application.setApplicationId(KeyUtil.getUniqueKey());
        application.setApplyData(new Date());
        application.setConfId(confId);
        application.setUserName(userName);
        application.setStatus(ApplicationStatusEnum.APPLICATION_STATUS_NEW.getCode());

        return saveOne(application);
    }

    @Override
    public List<ApplicationVo> getAllNewApplicationByConfId(String confId) {
        List<Application> applicationList=findListByConfId(confId);
        List<ApplicationVo> voList=new LinkedList<>();
        for (Application application:applicationList){
            ApplicationVo applicationVo=new ApplicationVo();
            try {
                applicationVo.setApplyDataStr(DateUtil.dateMinuteToStr(application.getApplyData()));
            }
            catch (Exception e){
                log.error("查询未通过的申请列表-时间转换异常：application={}",application);
            }
            applicationVo.setStatusStr(ApplicationStatusEnum.getByCode(application.getStatus()).getMessage());
        }
        return voList;
    }
}
