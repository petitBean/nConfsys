package org.wxz.confsysuser.service.serviceImp;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confsysdomain.nconfsysuser.RolePermission;
import org.wxz.confsysuser.service.RolePermissionService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/4/12 3:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class RolePermissionServiceImplTest {

    @Autowired
    private RolePermissionServiceImpl rolePermissionService;

    @Test
    void findAllByRoleIdIn() {
        List<String> strings= Arrays.asList("2");
        List<RolePermission> rolePermissions=rolePermissionService.findAllByRoleIdIn(strings);
        return;
    }
}