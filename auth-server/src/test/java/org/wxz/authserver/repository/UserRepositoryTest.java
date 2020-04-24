package org.wxz.authserver.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confsysdomain.nconfsysuser.User;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/4/21 16:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUserName() {
        User user=userRepository.findByUserName("wxz");
        return;
    }
}