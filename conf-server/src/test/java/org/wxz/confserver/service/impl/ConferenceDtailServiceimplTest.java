package org.wxz.confserver.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confserver.vo.DetailPageVo;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/4/22 21:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ConferenceDtailServiceimplTest {

    @Autowired
    private ConferenceDtailServiceimpl conferenceDtailServiceimpl;

    @Test
    void saveOne() {
    }

    @Test
    void findOneByDetailId() {
    }



    @Test
    void getConfDetailPageVo() {
        DetailPageVo vo=conferenceDtailServiceimpl.getConfDetailPageVo("0001");
        return;
    }
}