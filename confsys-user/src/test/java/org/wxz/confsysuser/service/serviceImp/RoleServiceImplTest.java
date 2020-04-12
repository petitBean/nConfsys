package org.wxz.confsysuser.service.serviceImp;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confsysdomain.nconfsysuser.Role;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/4/12 1:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class RoleServiceImplTest {

    @Autowired
    private RoleServiceImpl roleService;

    @Test
    void findAllRole() {
        List<Role> re=roleService.findAllRole();
        Assert.assertEquals(2,re.size());
    }
}