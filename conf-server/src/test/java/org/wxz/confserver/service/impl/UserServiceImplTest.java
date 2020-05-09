package org.wxz.confserver.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confserver.from.AddManagerFrom;
import org.wxz.confserver.from.CompleteUserInfoFrom;
import org.wxz.confsysdomain.nconfsysuser.User;
import org.wxz.nconfsyscommon.enums.RoleNameEnum;
import org.wxz.nconfsyscommon.utils.BcryptEncoderUtil;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/5/2 17:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void findByUserName() {
    }

    @Test
    void saveOne() {
    }

    @Test
    void updateOne() {
    }

    @Test
    void findOnePage() {
    }

    @Test
    void completeInfo() throws Exception {
        CompleteUserInfoFrom from=new CompleteUserInfoFrom();
        from.setEmail("577777");
        from.setUsername("13720131232");
        from.setAddress("xxxx");
        from.setName("wxz");
        from.setConfirmCode("128295");
        from.setGender("男");
        userService.completeInfo(from);

    }

    @Test
    void changePass() throws Exception{
        userService.changePass("13720131232","0123456","123456");
        System.out.println(BcryptEncoderUtil.toBcryptString("123456"));
    }

    @Test
    void addManager() throws Exception{
        AddManagerFrom from=new AddManagerFrom();
        from.setName("王兴泽");
        from.setPassword("123456");
        from.setUserName("12345678000");
        from.setRoleName(RoleNameEnum.ROLE_PROGESSOR.getRoleName());
        from.setConfId("0001");
        User re=  userService.addManager(from);
        return;
    }

}