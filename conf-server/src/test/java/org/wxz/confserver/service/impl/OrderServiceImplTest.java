package org.wxz.confserver.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confserver.from.OrderFrom;
import org.wxz.confserver.vo.FinancerVo;
import org.wxz.confsysdomain.nconfsysconf.PayOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/5/10 15:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;
    @Test
    void getHasPayList() throws Exception {
        List<FinancerVo> re=orderService.getHasPayList("1589133816201287586");
        List<FinancerVo> re1=orderService.getNonPayList("1589133816201287586");
        return;
    }

    @Test
    void getNonPayList() throws Exception{
        OrderFrom from=new OrderFrom();
        from.setAmount(90);
        from.setConfId("1589094239668741874");
        from.setUserName("13129967645");
        orderService.createOne(from);
    }
}