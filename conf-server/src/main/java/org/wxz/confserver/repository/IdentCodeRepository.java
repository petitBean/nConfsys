package org.wxz.confserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.common.IdentCode;

/**
 * @Author xingze Wang
 * @create 2020/5/2 16:11
 */
@Repository
public interface IdentCodeRepository extends JpaRepository<IdentCode,String> {

    IdentCode findByUserName(String userName);

    void deleteById(String  id);

    void deleteAllByUserName(String userName);

}
