package org.wxz.confserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.nconfsysconf.Order;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/9 14:29
 */
@Repository
public interface OrderRepository extends JpaRepository<Order,String> {

    Order findByConfIdAndUserName(String confId,String userName);

    List<Order> findAllByConfIdAndStatus(String confId,int status);


}
