package org.wxz.authserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.authserver.repository.UserRoleRepository;
import org.wxz.authserver.service.UserRoleService;
import org.wxz.confsysdomain.nconfsysuser.UserRole;


import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/12 2:32
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     * 通过userId 查找所有的userRole
     * @param userId
     * @return
     */
    @Override
    public List<UserRole> findAllUserRoleByUserId(String userId) {
        return userRoleRepository.findAllByUserId(userId);
    }

    @Override
    public UserRole saveOne(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }
}
