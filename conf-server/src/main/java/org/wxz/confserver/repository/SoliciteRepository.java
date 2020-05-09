package org.wxz.confserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.paper.Solicite;

/**
 * @Author xingze Wang
 * @create 2020/5/6 16:07
 */
@Repository
public interface SoliciteRepository extends JpaRepository<Solicite,String> {

    Solicite findByConfId(String confId);

}
