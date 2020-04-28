package org.wxz.confserver.service;

import org.wxz.confserver.vo.ApplicationVo;
import org.wxz.confsysdomain.nconfsysconf.Application;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/29 0:35
 */
public interface ApplicationService {

    Application findOneByUserNameAndConfId(String userName, String confId);

    List<Application> findListByConfId(String confId);

    List<Application> findListByUserName(String userName);

    Application saveOne(Application application) throws Exception;

    Application createOne(String userName,String confId) throws Exception;

    List<ApplicationVo> getAllNewApplicationByConfId(String confId);

}
