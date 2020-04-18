package org.wxz.paperserver.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wxz.confsysdomain.nconfsysuser.User;

/**
 * @Author xingze Wang
 * @create 2020/4/17 6:40
 */
public interface UserService {

    User findByUserName(String userName);

    User saveOne(User user);

    User updateOne(User newUser);

    Page<User> findOnePage(Pageable pageable);

}
