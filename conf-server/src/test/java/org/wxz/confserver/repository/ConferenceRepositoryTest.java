package org.wxz.confserver.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confsysdomain.nconfsysconf.Conference;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/4/22 21:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ConferenceRepositoryTest {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Test
    void findByConfId() {
        Date da=new Date();
        System.out.println(da);
        return;
    }
}