package org.wxz.authserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.authserver.enums.RoleEnum;
import org.wxz.authserver.from.AddManagerFrom;
import org.wxz.authserver.from.UserFrom;
import org.wxz.authserver.repository.UserRepository;
import org.wxz.authserver.service.UserService;
import org.wxz.authserver.util.BcryptEncoderUtil;
import org.wxz.confsysdomain.nconfsysuser.Role;
import org.wxz.confsysdomain.nconfsysuser.User;
import org.wxz.confsysdomain.nconfsysuser.UserRole;
import org.wxz.nconfsyscommon.enums.RoleNameEnum;
import org.wxz.nconfsyscommon.enums.UserStatusEnum;
import org.wxz.nconfsyscommon.exception.ConfException;
import org.wxz.nconfsyscommon.utils.KeyUtil;

import javax.transaction.Transactional;
import java.util.Date;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleServiceImpl userRoleService;

    @Autowired
    private RoleServiceImpl roleService;

    @Override
    public User findOneByUserName(String userName) throws Exception{
        return userRepository.findByUserName(userName);
    }

    /**
     * 根据用户名查找密码
     * @param userName
     * @return
     */
    @Override
    public String getPasswordByUserName(String userName) throws Exception{
        User user=null;
        try{
            user=findOneByUserName(userName);
        }
        catch (Exception e){
           throw new ConfException("用户名已存在");
        }

        if (user==null){
            return null;
        }
        return user.getPassword();
    }

    /**
     * 用户注册
     * @param userFrom
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public User userRegister(UserFrom userFrom) throws Exception {
        if (userFrom.getUserName()==null){
            log.error("用户注册失败-用户名为空");
            throw new ConfException("请求错误");
        }
        User newUser=null;
        synchronized (userFrom.getUserName()){
            User old=userRepository.findByUserName(userFrom.getUserName());
            if (old!=null){
                log.error("用户注册失败-用户已存在");
                throw new ConfException("用户已存在");
            }
             newUser=new User();
            BeanUtils.copyProperties(userFrom,newUser);
            newUser.setUserId(KeyUtil.getUniqueKey());
            newUser.setStatus(UserStatusEnum.NEW_USER_STATUS.getCode());
            newUser.setPassword(BcryptEncoderUtil.toBcryptString(newUser.getPassword()));
            newUser.setCreateDate(new Date());

            Role role=roleService.findByRoleName(RoleNameEnum.ROLE_USER.getRoleName());
            UserRole userRole=new UserRole();
            userRole.setRoleId(role.getRoleId());
            userRole.setUserId(newUser.getUserId());
            userRole=userRoleService.saveOne(userRole);
            newUser= userRepository.save(newUser);
        }
        return newUser;
    }

}
