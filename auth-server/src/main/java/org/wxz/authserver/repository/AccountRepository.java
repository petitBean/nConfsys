package org.wxz.authserver.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.wxz.authserver.entity.Account;

/**
 * 账户数据库操作类
 * MongoDB操作接口
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    /**
     * 根据用户名查找账户信息
     * @param username 用户名
     * @return 账户信息
     */
    Account findByUserName(String username);

    Account save(Account s);

    void deleteAll();
}
