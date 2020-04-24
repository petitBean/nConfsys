package org.wxz.authserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.wxz.authserver.repository.PermissionRepository;
import org.wxz.authserver.repository.RolePermissionRepository;
import org.wxz.authserver.repository.UserRepository;
import org.wxz.authserver.repository.UserRoleRepository;
import org.wxz.confsysdomain.nconfsysuser.Permission;
import org.wxz.confsysdomain.nconfsysuser.RolePermission;
import org.wxz.confsysdomain.nconfsysuser.UserRole;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author xingze Wang
 * @create 2020/4/16 13:36
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        org.wxz.confsysdomain.nconfsysuser.User findedUser=userRepository.findByUserName(userName);
        if (findedUser==null){
            log.info("认证授权-获取用户信息--null-- userName={}",userName);
            return null;
        }
        //根据userId查找 user_role
        List<UserRole> userRoleList=userRoleRepository.findAllByUserId(findedUser.getUserId());
        List<String> roleIdList=new ArrayList<>(userRoleList.size());
        if (userRoleList==null){
            return null;
        }
        //获取RoleIds
        for (UserRole userRole : userRoleList){
            roleIdList.add(userRole.getRoleId());
        }
        //查找role-permission
        List<RolePermission> rolePermissionList=rolePermissionRepository.findAllByRoleIdIn(roleIdList);
        if (rolePermissionList==null){
            return null;
        }
        //获取permissionsID
        Set<String> permissionIdSet=new HashSet<>(rolePermissionList.size());
        for (RolePermission rolePermission: rolePermissionList){
            permissionIdSet.add(rolePermission.getPermissionId());
        }
        //查找permissions
        List<Permission> permissionList=permissionRepository.findAllByPermissionIdIn(permissionIdSet);
        if (permissionList==null){
            return null;
        }
        //拼接permissionname
        String[] permissionNames=new String[permissionList.size()];
        int i=0;
        for (Permission permission:permissionList){
            permissionNames[i]=permission.getPermissionName();
            i++;
        }
        //System.out.println(permissionNames);
        /*UserDetails details= User.withUsername("wxz")
                .password("$2a$10$bbPG1TVXblUFA4BMVFJm9egaqe2oQpXDrhK3Fq.weSLCHnDOJya/O")
                //.password(findedUser.getPassword())
                .authorities("p1")
                .build();*/
        UserDetails details=User.withUsername(findedUser.getUserName())
                                .password(findedUser.getPassword())
                                .authorities(permissionNames)
                                 .build();
        return details;
    }
}
