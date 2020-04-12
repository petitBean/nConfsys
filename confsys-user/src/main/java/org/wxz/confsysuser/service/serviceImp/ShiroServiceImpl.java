package org.wxz.confsysuser.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confsysdomain.nconfsysuser.*;
import org.wxz.confsysuser.service.ShiroService;

import java.util.*;

/**
 * @Author xingze Wang
 * @create 2020/4/12 2:13
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private UserRoleServiceImpl userRoleService;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private RolePermissionServiceImpl rolePermissionService;
    @Autowired
    private PermissionServiceImpl permissionService;

    /**
     * 查找用户所有的所有权限
     * @param userName
     * @return
     */
    @Override
    public Set<String> getRolesByUserName(String userName) {
        //查找userId
        User user= userServiceImpl.findOneByUserName(userName);
        if (user==null){
            return null;
        }
        String userId=user.getUserId();
        //查找对应的所有的userRole
        List<UserRole> userRoleList=userRoleService.findAllUserRoleByUserId(userId);
        //查找所有的roleId
        List<String> roleIdList=new ArrayList<>(userRoleList.size());
        for (UserRole userRole:userRoleList){
            roleIdList.add(userRole.getRoleId());
        }
        //查找所有ID对应role
        List<Role> roleList=roleService.findAllByRoleIdIn(roleIdList);
        //获取roleName
        Set<String> roleNameSet=new HashSet<>(roleList.size());
        for (Role role:roleList){
            roleNameSet.add(role.getRoleName());
        }
        //查找所有的权限
        //遍历组装
        return roleNameSet;
    }

    /**
     * 根据userName获取用户权限集
     * @param userName
     * @return
     */
    @Override
    public Set<String> getPermissionsByUserName(String userName) {
        //查找userId
        User user= userServiceImpl.findOneByUserName(userName);
        if (user==null){
            return null;
        }
        String userId=user.getUserId();
        //查找对应的所有的userRole
        List<UserRole> userRoleList=userRoleService.findAllUserRoleByUserId(userId);
        //查找所有的roleId
        List<String> roleIdList=new ArrayList<>(userRoleList.size());
        if (userRoleList==null){
            return null;
        }
        for (UserRole userRole:userRoleList){
            roleIdList.add(userRole.getRoleId());
        }
        //查找所有role
        List<Role> roleList=roleService.findAllByRoleIdIn(roleIdList);
        if (roleList==null){
            return null;
        }
        Map<String,String> roleMap=new HashMap<>(roleList.size());
        for (Role role:roleList){
            roleMap.put(role.getRoleId(),role.getRoleName());
        }
        //获取所有roleID对应的rolePermission
        List<RolePermission> rolePermissionList=rolePermissionService.findAllByRoleIdIn(roleIdList);
        if (rolePermissionList==null){
            return null;
        }
        Set<String> permissionIdSet=new HashSet<>(rolePermissionList.size());
        for(RolePermission rolePermission:rolePermissionList){
            permissionIdSet.add(rolePermission.getPermissionId());
        }
        //获取permissionId对应的permission
        List<Permission> permissionList=permissionService.findAllByPermissionIdIn(permissionIdSet);
        //将permission放入map
        Map<String,String> permissionMap=new HashMap<>(permissionList.size());
        for (Permission permission:permissionList){
            permissionMap.put(permission.getPermissionId(),permission.getPermissionName());
        }
        //遍历role-permission,添加记录
        Set<String> permissionSet=new HashSet<>();
        for (RolePermission rolePermission :rolePermissionList){
            permissionSet.add(roleMap.get(rolePermission.getRoleId())
                                 +":"
                                 +permissionMap.get(rolePermission.getPermissionId()));
        }
        return permissionSet;
    }

    /**
     * 根用户名获取密码
     * @param userName
     * @return
     */
    @Override
    public String getPasswordByUserName(String userName) {
        return userServiceImpl.getPasswordByUserName(userName);
    }


}
