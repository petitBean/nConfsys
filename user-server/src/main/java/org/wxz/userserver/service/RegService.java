package org.wxz.userserver.service;

import org.wxz.confsysdomain.nconfsysuser.User;
import org.wxz.userserver.dto.UserDto;

/**
 * @Author xingze Wang
 * @create 2020/4/17 7:11
 */
public interface RegService {

    User userRegister(UserDto userDto);

}
