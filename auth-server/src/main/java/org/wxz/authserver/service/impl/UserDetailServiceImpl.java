package org.wxz.authserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.wxz.authserver.repository.UserRepository;

/**
 * @Author xingze Wang
 * @create 2020/4/16 13:36
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        /*org.wxz.confsysdomain.nconfsysuser.User findedUser=userRepository.findByUserName(userName);
        if (findedUser==null){
            log.info("认证授权-获取用户信息--null-- userName={}",userName);
            return null;
        }*/
        UserDetails details= User.withUsername("wxz")
                .password("$2a$10$bbPG1TVXblUFA4BMVFJm9egaqe2oQpXDrhK3Fq.weSLCHnDOJya/O")
                //.password(findedUser.getPassword())
                .authorities("p1")
                .build();
        return details;
    }
}
