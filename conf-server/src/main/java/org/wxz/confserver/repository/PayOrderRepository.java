package org.wxz.confserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.nconfsysconf.PayOrder;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/9 14:29
 */
@Repository
public interface PayOrderRepository extends JpaRepository<PayOrder,String> {
    PayOrder findByConfIdAndUserName(String confId,String userName);

    List<PayOrder> findAllByConfIdAndStatus(String confId,int status);


}
