package org.wxz.confsysuser.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confsysdomain.nconfsysuser.Role;
import org.wxz.confsysdomain.nconfsysuser.User;
import org.wxz.confsysdomain.nconfsysuser.UserRole;
import org.wxz.confsysuser.repository.RoleRepository;
import org.wxz.confsysuser.service.RoleService;
import org.wxz.confsysuser.service.UserRoleService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
