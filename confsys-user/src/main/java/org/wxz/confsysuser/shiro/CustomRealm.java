package org.wxz.confsysuser.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.wxz.confsysuser.enums.AccountExceptionEnum;
import org.wxz.confsysuser.exception.AccountException;
import org.wxz.confsysuser.service.serviceImp.ShiroServiceImpl;
import org.wxz.confsysuser.utils.HashCredentialUtil;

import java.util.Set;

/**
 * @Author xingze Wang
 * @create 2020/4/11 20:53
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private ShiroServiceImpl shiroService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName=(String)principalCollection.getPrimaryPrincipal();
        //从数据库或者缓存获取角色数据
        Set<String> roles=getRolesByUserName(userName);
        //从数据库或者缓存获取权限数据
        Set<String> permissions=getPermissionsByUserName(userName);
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
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
        String password=getPasswordByUserName(userName);
        //密码为空，不存在
        if (userName==null ){
            log.warn("登录认证—用户名为空");
            throw new AccountException(AccountExceptionEnum.ACCOUNT_USERNAME_EMPTY);
        }
        else if(userPassword==null){
            log.warn("登录认证—密码为空： userName={}",userName);
            throw new AccountException(AccountExceptionEnum.ACCOUNT_PASSWORD_EMPTY);
        }
        else if (!HashCredentialUtil.toMD5(userPassword,userName).equals(password)){
            log.warn("登录认证—密码错误： userName={},password={}",userName,userPassword);
            throw new AccountException(AccountExceptionEnum.ACCOUNT_PASSWORD_ERROR);
        }
        return new SimpleAuthenticationInfo(userName,password, ByteSource.Util.bytes(HashCredentialUtil.getSalt(userName)),getName());
    }

    /**
     * 从数据库查询用户密码
     * @param userName
     * @return
     */
    private String getPasswordByUserName(String userName){
        return shiroService.getPasswordByUserName(userName);
    }

    /**
     *获取用户的角色集
     * @param userName
     * @return
     */
    private Set<String> getRolesByUserName(String userName){
        Set<String> roles= shiroService.getRolesByUserName(userName);
        if (roles==null){
            log.error("获取用户角色：角色为空！userName={}",userName);
        }
        return roles;
    }

    /**
     * 获取用户的权限集
     * @param userName
     * @return
     */
    private Set<String> getPermissionsByUserName(String userName){
        Set<String> permissions=shiroService.getPermissionsByUserName(userName);
        if (permissions==null){
            log.info("获取用户权限：权限为空！ userName={}",userName);
        }
        return permissions;
    }



}
