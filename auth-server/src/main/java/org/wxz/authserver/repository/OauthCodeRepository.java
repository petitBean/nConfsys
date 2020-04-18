package org.wxz.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.authserver.entity.OauthCode;

/**
 * @Author xingze Wang
 * @create 2020/4/16 23:23
 */
@Repository
public interface OauthCodeRepository extends JpaRepository<OauthCode,String> {
}
