package org.wxz.authserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.authserver.repository.UserRepository;
import org.wxz.authserver.service.UserService;
import org.wxz.confsysdomain.nconfsysuser.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOneByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    /**
     * 根据用户名查找密码
     * @param userName
     * @return
     */
    @Override
    public String getPasswordByUserName(String userName) {
        User user=findOneByUserName(userName);
        if (user==null){
            return null;
        }
        return user.getPassword();
    }

}
