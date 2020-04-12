package org.wxz.confsysuser.service.serviceImp;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/4/12 3:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ShiroServiceImplTest {

    @Autowired
    private ShiroServiceImpl shiroService;

    @Test
    void getRolesByUserName() {
        Set<String> strings=shiroService.getRolesByUserName("wxz");
        return;
    }

    @Test
    public void getPermissionsByUserName(){
        Set<String> strings=shiroService.getPermissionsByUserName("wxz");
        return;
    }
}