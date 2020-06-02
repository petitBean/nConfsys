package org.wxz.confserver.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confsysdomain.nconfsysconf.Conference;
import org.wxz.nconfsyscommon.enums.ConfStatusEnum;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    @Test
    void findv(){
        List<String> list= Arrays.asList("1589215620708655663","1589215895109964241","1589216982606264109");
        Sort.Order order=new Sort.Order(Sort.Direction.DESC,"startTime");
        List<Sort.Order> orderList=Arrays.asList(order);
        Sort sort=Sort.by(orderList);
        Pageable pageable= PageRequest.of(0,5,sort);
        List<Integer> statusList=Arrays.asList(ConfStatusEnum.NEW_CONF_STATUS.getCode(),ConfStatusEnum.COMPLETED_DETAIL_STATUS.getCode(),ConfStatusEnum.PAYING_CONF_STATUS.getCode(),ConfStatusEnum.PAPER_COLLECTINT_STATUS.getCode());
        List<Conference> conferenceList=conferenceRepository.findAllByConfIdInAndStatusIn(list,statusList,pageable);
        return;
    }
}