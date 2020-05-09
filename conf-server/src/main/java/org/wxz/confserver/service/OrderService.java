package org.wxz.confserver.service;

import org.wxz.confserver.from.OrderFrom;
import org.wxz.confserver.vo.FinancerVo;
import org.wxz.confsysdomain.nconfsysconf.Order;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/9 14:32
 */
public interface OrderService {

    Order findOneByConfIdAndUserName(String confId, String userName) throws Exception;

    List<Order> findListByConfIdAndStatus(String confId, int status) throws Exception;

    List<FinancerVo> getHasPayList(String confId)throws Exception;

    List<FinancerVo> getNonPayList(String confId)throws Exception;

    Order createOne(OrderFrom orderFrom)throws Exception;

}
