package org.wxz.confsysuser.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/4/11 21:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomRealmTest {

    @Test
    public void estAuthentication(){
        CustomRealm customRealm=new CustomRealm();
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        securityManager.setRealm(customRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("username","123456");
        subject.login(token);  //用户执行
        System.out.println("isAuthentication?"+subject.isAuthenticated());
        subject.checkRole("admin"); //用户执行传入角色
        subject.checkPermissions("user:add","user:delete"); //用户传入权限
        return;
    }


}