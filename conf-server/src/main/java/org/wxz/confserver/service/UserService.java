package org.wxz.confserver.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wxz.confserver.from.AddManagerFrom;
import org.wxz.confserver.from.CompleteUserInfoFrom;
import org.wxz.confserver.from.UserFrom;
import org.wxz.confserver.vo.ApplicationManageVo;
import org.wxz.confserver.vo.UserVo;
import org.wxz.confsysdomain.nconfsysuser.User;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/17 6:40
 */
public interface UserService {

    User findByUserName(String userName);

    User saveOne (User user) throws Exception;

    User updateOne(User newUser);

    Page<User> findOnePage(Pageable pageable);

    boolean completeInfo(CompleteUserInfoFrom from)throws Exception;

    boolean changePass(String userName,String oldPass,String newPass) throws Exception;

    User userRegister(UserFrom userFrom) throws Exception;

    User addManager(AddManagerFrom from) throws Exception;

    List<User> findListByUserNameIn(List<String> userNameList);

    List<ApplicationManageVo> getPassedUserList(String confId)throws Exception;

    List<UserVo> getVoListByConfIdAndRoleName(String confId,String RoleName)throws Exception;

    void findPass(String userName,String email)throws Exception;

}
