package org.wxz.confserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confserver.from.ApplicationFrom;
import org.wxz.confserver.repository.ApplicationRepository;
import org.wxz.confserver.service.ApplicationService;
import org.wxz.confserver.vo.ApplicationManageVo;
import org.wxz.confserver.vo.ApplicationVo;
import org.wxz.confsysdomain.nconfsysconf.Application;
import org.wxz.confsysdomain.nconfsysuser.User;
import org.wxz.nconfsyscommon.enums.ApplicationStatusEnum;
import org.wxz.nconfsyscommon.exception.ConfException;
import org.wxz.nconfsyscommon.utils.DateUtil;
import org.wxz.nconfsyscommon.utils.KeyUtil;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @Author xingze Wang
 * @create 2020/4/29 0:37
 */
@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Application findOneByUserNameAndConfId(String userName, String confId) {
        return applicationRepository.findByUserNameAndConfId(userName,confId);
    }

    @Override
    public List<Application> findListByConfId(String confId) {
        return applicationRepository.findAllByConfId(confId);
    }

    @Override
    public Application findOneByApplicationId(String applicationId) {
        return applicationRepository.findByApplicationId(applicationId);
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
    @Transactional(rollbackOn = Exception.class)
    public Application createOne(ApplicationFrom from) throws Exception {
        Application applicationFoubded=applicationRepository.findByUserNameAndConfId(from.getUserName(),from.getConfId());
        if (applicationFoubded!=null){
          throw new ConfException("该用户已经申请!");
        }
        Application application=new Application();
        application.setApplicationId(KeyUtil.getUniqueKey());
        application.setApplyData(new Date());
        BeanUtils.copyProperties(from,application);
        application.setStatus(ApplicationStatusEnum.APPLICATION_STATUS_NEW.getCode());
        Application re=null;
        Application applicationFoubded2=applicationRepository.findByUserNameAndConfId(from.getUserName(),from.getConfId());
        if (applicationFoubded2!=null){
            throw new ConfException("该用户已经申请!");
        }
        try {
            re=saveOne(application);
        }catch (Exception e){
            log.error("用户申请参会-失败-存贮失败");
            throw new ConfException("数据存储失败！");
        }

        return re;
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

    /*
    根据用户名和申请状态查找
     */
    @Override
    public List<Application> findListByUserNameAndAndStatus(String userName, int status) {
        return applicationRepository.findAllByUserNameAndAndStatus(userName,status);
    }


    /**
     * 查询未审核和未通过得申请vo
     * @param confId
     * @return
     * @throws Exception
     */
    @Override
    public List<ApplicationManageVo> getApplicationManageVo(String confId) throws Exception {
        if(confId==null){
            log.error("查询申请管理Vo-错误-会议id为空！");
            throw new ConfException("参数为空！");
        }
        List<ApplicationManageVo> voList=new LinkedList<>();
        voList.addAll(getApplicationManageVoByConfIdAndStatus(confId,ApplicationStatusEnum.APPLICATION_STATUS_NEW));
        voList.addAll(getApplicationManageVoByConfIdAndStatus(confId,ApplicationStatusEnum.APPLICATION_STATUS_REFUSED));
        return voList;
    }

    /**
     * 通过会议申请
     * @param applicationId
     * @throws Exception
     */
    @Override
    public void passApply(String applicationId) throws Exception {
        dealApply(applicationId,ApplicationStatusEnum.APPLICATION_STATUS_PASSED);
    }

    /**
     * 拒绝会议申请
     * @param applicationId
     * @throws Exception
     */
    @Override
    public void refuseApply(String applicationId) throws Exception {
         dealApply(applicationId,ApplicationStatusEnum.APPLICATION_STATUS_REFUSED);
    }

    @Override
    public void dealApply(String applicationId,ApplicationStatusEnum statusEnum) throws Exception {
        if (applicationId==null){
            log.error("处理会议申请-错误-applicationId=null");
            throw new ConfException("系统异常！");

        }
        Application application=findOneByApplicationId(applicationId);
        if (application==null){
            log.error("处理会议申请-错误-application=null,applicationId={}",applicationId);
            throw new ConfException("系统异常！申请记录不存在!");

        }
        application.setStatus(statusEnum.getCode());
        try {
            applicationRepository.save(application);
        }
        catch (Exception e){
            log.error("处理会议申请-失败-申请状态保存失败！application={}",application);
            throw new ConfException("申请状态保存失败");
        }
        log.info("处理会议申请-成功！");
    }

    @Override
    public List<Application> findListByConfIdAndStatusAndUserNameNotIn(String confId, int status, List<String> userNameList) {
        return applicationRepository.findAllByConfIdAndStatusAndUserNameNotIn(confId,status,userNameList);
    }

    @Override
    public List<Application> findAllByConfIdAndStatus(String confId, int status) {
        return applicationRepository.findAllByConfIdAndStatus(confId,status);
    }


    public List<ApplicationManageVo> getApplicationManageVoByConfIdAndStatus(String confId,ApplicationStatusEnum statusEnum) throws Exception{
        List<Application> applicationList=applicationRepository.findAllByConfIdAndStatus(confId,statusEnum.getCode());
        if (applicationList==null){
            log.error("查询申请管理Vo-结果为空！conId={}",confId);
            return null;
        }
        List<String> usernameList=new LinkedList<>();
        Map<String,Application> applicationMap=new HashMap();
        for (Application application:applicationList){
            usernameList.add(application.getUserName());
            applicationMap.put(application.getUserName(),application);
        }
        List<User> userList=userService.findListByUserNameIn(usernameList);
        if (userList==null){
            log.error("查询申请管理Vo-用户信息不存在！userNameList={}",usernameList);
            throw new ConfException("用户列表为空！");
        }

        List<ApplicationManageVo> voList=new LinkedList<>();
        for(User user:userList){
            ApplicationManageVo vo=new ApplicationManageVo();
            BeanUtils.copyProperties(user,vo);
            Application application=applicationMap.get(user.getUserName());
            vo.setStatusStr(ApplicationStatusEnum.getByCode(application.getStatus()).getMessage());
            vo.setApplicationId(application.getApplicationId());
            vo.setReason(application.getReason());
            voList.add(vo);
        }
        return voList;
    }

}
