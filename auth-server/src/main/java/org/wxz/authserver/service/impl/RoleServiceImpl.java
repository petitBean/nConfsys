package org.wxz.authserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.authserver.repository.RoleRepository;
import org.wxz.authserver.service.RoleService;
import org.wxz.confsysdomain.nconfsysuser.Role;


import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/12 1:45
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    /**
     * 查找所有权限
     * @return
     */
    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }


    /**
     * 查找对应所有roleId的role对象
     * @param roleIdList
     * @return
     */
    @Override
    public List<Role> findAllByRoleIdIn(List<String> roleIdList) {
        return roleRepository.findAllByRoleIdIn(roleIdList);
    }


}
