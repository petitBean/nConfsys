package org.wxz.confserver.service;

import org.wxz.confserver.from.OrderFrom;
import org.wxz.confserver.vo.FinancerVo;
import org.wxz.confsysdomain.nconfsysconf.PayOrder;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/9 14:32
 */
public interface OrderService {

    PayOrder findOneByConfIdAndUserName(String confId, String userName) throws Exception;

    List<PayOrder> findListByConfIdAndStatus(String confId, int status) throws Exception;

    List<FinancerVo> getHasPayList(String confId)throws Exception;

    List<FinancerVo> getNonPayList(String confId)throws Exception;

    PayOrder createOne(OrderFrom orderFrom)throws Exception;

}
