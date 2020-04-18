package org.wxz.userserver.service.impl;

import org.springframework.stereotype.Service;
import org.wxz.confsysdomain.nconfsysuser.User;
import org.wxz.userserver.dto.UserDto;
import org.wxz.userserver.service.RegService;

/**
 * @Author xingze Wang
 * @create 2020/4/17 7:16
 */
@Service
public class RegServiceImpl implements RegService {

    @Override
    public User userRegister(UserDto userDto) {
        User user=new User();

        return null;
    }
}
