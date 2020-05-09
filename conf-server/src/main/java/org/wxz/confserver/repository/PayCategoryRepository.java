package org.wxz.confserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.nconfsysconf.PayCategory;

/**
 * @Author xingze Wang
 * @create 2020/5/9 6:20
 */
@Repository
public interface PayCategoryRepository extends JpaRepository<PayCategory,String> {

    PayCategory findByConfId(String confId);

}
