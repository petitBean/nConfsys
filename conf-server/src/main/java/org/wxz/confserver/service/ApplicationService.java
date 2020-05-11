package org.wxz.confserver.service;

import org.wxz.confserver.from.ApplicationFrom;
import org.wxz.confserver.vo.ApplicationManageVo;
import org.wxz.confserver.vo.ApplicationVo;
import org.wxz.confsysdomain.nconfsysconf.Application;
import org.wxz.nconfsyscommon.enums.ApplicationStatusEnum;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/29 0:35
 */
public interface ApplicationService {

    Application findOneByUserNameAndConfId(String userName, String confId);

    List<Application> findListByConfId(String confId);

    Application findOneByApplicationId(String applicationId);

    List<Application> findListByUserName(String userName);

    Application saveOne(Application application) throws Exception;

    Application createOne(ApplicationFrom from) throws Exception;

    List<ApplicationVo> getAllNewApplicationByConfId(String confId);

    List<Application> findListByUserNameAndAndStatus(String userName,int status);

    List<ApplicationManageVo> getApplicationManageVo(String confId)throws Exception;

    void passApply(String applicationId)throws Exception;

    void refuseApply(String applicationId) throws Exception;

    void dealApply(String applicationId, ApplicationStatusEnum statusEnum)throws Exception;

    List<Application> findListByConfIdAndStatusAndUserNameNotIn(String confId,int status,List<String> userNameList);

    List<Application> findAllByConfIdAndStatus(String confId,int status);

}
