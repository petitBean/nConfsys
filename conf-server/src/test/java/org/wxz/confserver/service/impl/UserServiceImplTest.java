package org.wxz.confserver.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
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
        from.setUserName("12345678009");
        from.setRoleName(RoleNameEnum.ROLE_PROGESSOR.getRoleName());
        from.setConfId("1589094239668741874");
        User re=  userService.addManager(from);
         from=new AddManagerFrom();
        from.setName("王兴泽");
        from.setPassword("123456");
        from.setUserName("12345678005");
        from.setRoleName(RoleNameEnum.ROLE_PROGESSOR.getRoleName());
        from.setConfId("1589094239668741874");
        userService.addManager(from);
         from=new AddManagerFrom();
        from.setName("王兴泽");
        from.setPassword("123456");
        from.setUserName("12345678004");
        from.setRoleName(RoleNameEnum.ROLE_PROGESSOR.getRoleName());
        from.setConfId("1589094239668741874");
        userService.addManager(from);
         from=new AddManagerFrom();
        from.setName("王兴泽");
        from.setPassword("123456");
        from.setUserName("12345678001");
        from.setRoleName(RoleNameEnum.ROLE_PROGESSOR.getRoleName());
        from.setConfId("1589094239668741874");
          userService.addManager(from);
        from=new AddManagerFrom();
        from.setName("王兴泽");
        from.setPassword("123456");
        from.setUserName("12345678002");
        from.setRoleName(RoleNameEnum.ROLE_PROGESSOR.getRoleName());
        from.setConfId("1589094239668741874");
        userService.addManager(from);
       from=new AddManagerFrom();
        from.setName("王兴泽");
        from.setPassword("123456");
        from.setUserName("12345678003");
        from.setRoleName(RoleNameEnum.ROLE_PROGESSOR.getRoleName());
        from.setConfId("1589094239668741874");
        userService.addManager(from);
        return;
    }

    @Test
    void findpass() throws Exception{

        userService.findPass("xxxx","841246785@qq.com");
    }

}