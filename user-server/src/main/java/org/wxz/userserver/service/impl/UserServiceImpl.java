package org.wxz.userserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wxz.confsysdomain.nconfsysuser.User;
import org.wxz.userserver.repository.UserRepository;
import org.wxz.userserver.service.UserService;

/**
 * @Author xingze Wang
 * @create 2020/4/17 7:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User saveOne(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateOne(User newUser) {
        return saveOne(newUser);
    }

    @Override
    public Page<User> findOnePage(Pageable pageable) {
        return null;
    }
}
