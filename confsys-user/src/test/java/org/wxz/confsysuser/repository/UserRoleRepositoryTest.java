package org.wxz.confsysuser.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confsysdomain.nconfsysuser.UserRole;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/4/12 2:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserRoleRepositoryTest {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    public void findAllByUserIdIn(){
        List<UserRole> list=userRoleRepository.findAllByUserId("123");
        Assert.assertEquals(2,list.size());
    }
}