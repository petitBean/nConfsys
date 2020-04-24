package org.wxz.authserver.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/4/21 16:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserDetailServiceImplTest {

    @Qualifier("userDetailServiceImpl")
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Test
    void loadUserByUsername() {
        UserDetails userDetails=userDetailService.loadUserByUsername("wxz");
        return;
    }
}