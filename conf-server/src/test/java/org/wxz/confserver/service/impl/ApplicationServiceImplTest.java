package org.wxz.confserver.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confserver.vo.ApplicationManageVo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/5/4 2:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationServiceImplTest {

    @Autowired
    private ApplicationServiceImpl applicationService;

    @Test
    void findOneByUserNameAndConfId() {
    }

    @Test
    void findListByConfId() {
    }

    @Test
    void findListByUserName() {
    }

    @Test
    void saveOne() {
    }

    @Test
    void createOne() {
    }

    @Test
    void getAllNewApplicationByConfId() {
    }

    @Test
    void findListByUserNameAndAndStatus() {
    }

    @Test
    void getApplicationManageVo() {
    }

    @Test
    void getApplicationManageVoByConfIdAndStatus() throws Exception {
        List<ApplicationManageVo> voList=applicationService.getApplicationManageVo("0001");
        return;
    }
}