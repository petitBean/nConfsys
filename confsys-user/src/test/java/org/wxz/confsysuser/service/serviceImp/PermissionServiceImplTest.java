package org.wxz.confsysuser.service.serviceImp;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confsysdomain.nconfsysuser.Permission;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/4/12 1:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class PermissionServiceImplTest {

    @Autowired
    private PermissionServiceImpl permissionService;

    @Test
    void findAllPermission() {
        List<Permission>  re=permissionService.findAllPermission();
        Assert.assertEquals(2,re.size());
    }

    public void findAllByPermissionIdIn(){

    }
}