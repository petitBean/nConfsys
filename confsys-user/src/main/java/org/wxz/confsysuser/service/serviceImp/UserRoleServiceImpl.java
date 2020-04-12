package org.wxz.confsysuser.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confsysdomain.nconfsysuser.UserRole;
import org.wxz.confsysuser.repository.UserRoleRepository;
import org.wxz.confsysuser.service.UserRoleService;

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
}
