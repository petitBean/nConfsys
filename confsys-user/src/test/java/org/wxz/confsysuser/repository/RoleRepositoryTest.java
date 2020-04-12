package org.wxz.confsysuser.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confsysdomain.nconfsysuser.Role;
import org.wxz.confsysdomain.nconfsysuser.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/4/12 1:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findAll() {
        List<Role> result=roleRepository.findAll();
        Assert.assertEquals(2,result.size());
    }
}