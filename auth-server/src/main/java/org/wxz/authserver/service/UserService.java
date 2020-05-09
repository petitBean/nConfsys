package org.wxz.authserver.service;

import org.wxz.authserver.from.AddManagerFrom;
import org.wxz.authserver.from.UserFrom;
import org.wxz.confsysdomain.nconfsysuser.User;

/**
 * @Author xingze Wang
 * @create 2020/4/16 13:35
 */
public interface UserService {


    public User findOneByUserName(String userName);

    /**
     * 根据用户名查找密码
     * @param userName
     * @return
     */

    public String getPasswordByUserName(String userName);

    User userRegister(UserFrom userFrom) throws Exception;


}
