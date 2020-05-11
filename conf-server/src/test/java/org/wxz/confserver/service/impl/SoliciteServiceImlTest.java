package org.wxz.confserver.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confserver.from.ViewDemandFrom;
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
        Solicite solicite=soliciteServiceIml.createOne("1589094239668741874","格式要求3",new Date(),new Date());
        return;
    }

    @Test
    void add() throws Exception{
        ViewDemandFrom from=new ViewDemandFrom();
        from.setConfId("1589094239668741874");
        from.setViewDemand("dddddddd");
        soliciteServiceIml.addViewDemand(from);
    }

}