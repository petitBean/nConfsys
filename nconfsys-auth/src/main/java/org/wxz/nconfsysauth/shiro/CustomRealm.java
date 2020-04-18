package org.wxz.nconfsysauth.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.wxz.confsysserviceapi.user_service.AuthorizationApi;
import org.wxz.nconfsysauth.util.SpringUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author xingze Wang
 * @create 2020/4/11 20:53
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    private AuthorizationApi authorizationApi;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName=(String)principalCollection.getPrimaryPrincipal();
        authorizationApi=SpringUtil.getBean(AuthorizationApi.class);
        HashMap<String,Set<String>> authInfoMap=authorizationApi.getAuthInfoByUserName(userName);
        //TODO
        /*Set<String> roles=getRolesByUserName(userName);*/
        //Set<String> roles=new HashSet<>();
        //roles.add("teacher");
        //从数据库或者缓存获取权限数据
        //TODO
        /*Set<String> permissions=getPermissionsByUserName(userName);*/
        //Set<String> permissions=new HashSet<>();
        //permissions.add("teacher:delete");
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        System.out.println("授权-------");
        authorizationInfo.setRoles(authInfoMap.get("roles"));
        authorizationInfo.setStringPermissions(authInfoMap.get("permissions"));
        System.out.println("---------xxxx-----------xxx----------xxx-------xxx---------------xxx--------xxx-------------------");
        return authorizationInfo;
    }

    /**
     * 登录认证
     * @param authenticationToken 主题传过来的认证信息
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.从用户传过来的认证信息获取用户名.密码
        String userName=(String) authenticationToken.getPrincipal();
        String userPassword = new String((char[]) authenticationToken.getCredentials());
        //2.根据用户名获取用户密码,密码为空或者密码错误会抛出异常
        String password="123456";
        System.out.println("认证");
        //密码为空，不存在
        if (userName==null ){
            log.warn("登录认证—用户名为空");
        }
        else if(userPassword==null){
            log.warn("登录认证—密码为空： userName={}",userName);
        }
        else if (!userPassword.equals(password)){
            log.warn("登录认证—密码错误： userName={},password={}",userName,userPassword);
        }
        return new SimpleAuthenticationInfo(userName,password,getName());
    }


   /* private Set<String> getRolesByUserName(String userName){
        Set<String> roles= shiroService.getRolesByUserName(userName);
        if (roles==null){
            log.error("获取用户角色：角色为空！userName={}",userName);
        }
        return roles;
    }

    *//**
     * 获取用户的权限集
     * @param userName
     * @return
     *//*
    private Set<String> getPermissionsByUserName(String userName){
        Set<String> permissions=shiroService.getPermissionsByUserName(userName);
        if (permissions==null){
            log.info("获取用户权限：权限为空！ userName={}",userName);
        }
        return permissions;
    }
*/

    public HashMap<String, Set<String>> getAuthInfoByUserName(String userName) {
        authorizationApi= SpringUtil.getBean(AuthorizationApi.class);
        return null;
    }

}
