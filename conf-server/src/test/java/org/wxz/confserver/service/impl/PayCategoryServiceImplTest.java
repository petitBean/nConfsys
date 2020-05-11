package org.wxz.confserver.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confserver.from.PayCategoryFrom;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/5/10 18:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class PayCategoryServiceImplTest {

    @Autowired
    private PayCategoryServiceImpl payCategoryService;

    @Test
    void saveOne() {
    }

    @Test
    void findOneByConfId() {
    }

    @Test
    void createOne() throws Exception {
        PayCategoryFrom from=new PayCategoryFrom();
        from.setAmount(90);
        from.setConfId("1589094239668741874");
        Date date[]=new Date[2];
        date[1]=new Date();
        date[0]=new Date();
        from.setDates(date);
        payCategoryService.createOne(from);
    }
}