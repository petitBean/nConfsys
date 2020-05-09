package org.wxz.authserver.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.authserver.from.AddManagerFrom;
import org.wxz.authserver.from.UserFrom;
import org.wxz.confsysdomain.nconfsysuser.User;
import org.wxz.nconfsyscommon.enums.RoleNameEnum;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/4/22 13:39
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void findOneByUserName() {
    }

    @Test
    void getPasswordByUserName() {
    }

    @Test
    void userRegister() throws Exception {
        UserFrom userFrom=new UserFrom("xxx","123456");
        User user= userService.userRegister(userFrom);
       return;
    }


}