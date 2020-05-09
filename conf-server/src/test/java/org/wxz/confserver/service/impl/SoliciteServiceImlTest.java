package org.wxz.confserver.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confsysdomain.paper.Solicite;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/5/6 22:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class SoliciteServiceImlTest {


    @Autowired
    private SoliciteServiceIml soliciteServiceIml;

    @Test
    void findOneByConfId() {
    }

    @Test
    void createOne() throws Exception{
        Solicite solicite=soliciteServiceIml.createOne("0001","格式要求",new Date(),new Date());
        return;
    }
}