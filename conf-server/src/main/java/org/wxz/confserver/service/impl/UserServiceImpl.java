package org.wxz.confserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wxz.confserver.from.AddManagerFrom;
import org.wxz.confserver.from.CompleteUserInfoFrom;
import org.wxz.confserver.from.UserFrom;
import org.wxz.confserver.repository.UserRepository;
import org.wxz.confserver.service.UserService;
import org.wxz.confserver.vo.ApplicationManageVo;
import org.wxz.confserver.vo.UserVo;
import org.wxz.confsysdomain.common.IdentCode;
import org.wxz.confsysdomain.nconfsysuser.Role;
import org.wxz.confsysdomain.nconfsysuser.User;
import org.wxz.confsysdomain.nconfsysuser.UserRole;
import org.wxz.confsysdomain.relation.ConferenceUer;
import org.wxz.nconfsyscommon.enums.ApplicationStatusEnum;
import org.wxz.nconfsyscommon.enums.RoleNameEnum;
import org.wxz.nconfsyscommon.enums.SexEnum;
import org.wxz.nconfsyscommon.enums.UserStatusEnum;
import org.wxz.nconfsyscommon.exception.ConfException;
import org.wxz.nconfsyscommon.utils.BcryptEncoderUtil;
import org.wxz.nconfsyscommon.utils.KeyUtil;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleServiceImpl userRoleService;

    @Autowired
    private RoleServiceImpl roleService;


    @Autowired
    private IdentCodeServiceImpl identCodeService;

    @Autowired
    private ConferenceUserServiceImpl conferenceUserService;

    @Autowired
    private ApplicationServiceImpl applicationService;

    @Autowired
    private IMailServiceImpl iMailService;


    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public User saveOne(User user) throws Exception {
        try {
            return userRepository.save(user);
        }
        catch (Exception e){
            throw new ConfException("用户存储失败！");
        }
    }

    @Override
    public User updateOne(User newUser) {
       User result=null;
        try {
           result= saveOne(newUser);
        }catch (Exception e ){
           log.error("用户更新失败-保存失败-user={}",newUser); ;
        }
        return result;
    }

    @Override
    public Page<User> findOnePage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * 完善用户信息
     * @param from
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean completeInfo(CompleteUserInfoFrom from) throws Exception {
        User user=null;
        try {
            user=findByUserName(from.getUsername());
        }catch (Exception e){
            log.error(from.getUsername());
            throw new ConfException("用户信息查询失败！");
        }
        if (user==null){
            throw new ConfException("用户基本信息不存在");
        }
        //验证码对比
        IdentCode identCode=null;
        try {
          identCode=  identCodeService.findOneByUserName(from.getUsername());
        }catch (Exception e){
            throw new ConfException("验证码不正确！");
        }
        if (identCode==null||!identCode.getCode().equals(from.getConfirmCode())){
            throw new ConfException("验证码不正确！");
        }
        identCodeService.deleteAllByUserName(from.getUsername());
        //
        log.info(user.toString());
        user.setEmail(from.getEmail());
        user.setPhone(from.getPhone());
        user.setName(from.getName());
        user.setAddress(from.getAddress());
        user.setSex(SexEnum.getByMsg(from.getGender()).getCode());
        user.setStatus(UserStatusEnum.COMPLETE_USER_STATUS.getCode());
        saveOne(user);
        return true;
    }

    @Override
    public boolean changePass(String userName, String oldPass, String newPass) throws Exception {
        if (userName==null|| oldPass==null || newPass==null){
            log.error("用户修改密码-错误-参数位空：username={}",userName);
            throw new ConfException("参数错误");
        }
        if (oldPass.equals(newPass)){
            log.error("用户修改密码-错误-新旧密码相同：username={}",userName);
            throw new ConfException("新密码与旧密码相同");
        }
        User user=findByUserName(userName);
        if (user==null){
            log.error("用户修改密码-错误-用户不存在：username={}",userName);
            throw new ConfException("用户不存在！");
        }
        String passEncoded= BcryptEncoderUtil.toBcryptString(newPass);
        if (!BcryptEncoderUtil.compare(oldPass,user.getPassword())){
            log.error("用户修改密码-错误-密码错误：username={}",userName);
            throw new ConfException("密码错误！");
        }
        log.info("n"+newPass+'o'+oldPass);
        user.setPassword(passEncoded);
        try {
            userRepository.save(user);
        }catch (Exception e){
            log.error("用户修改密码-失败-保存失败：username={}",userName);
            throw new ConfException("更改失败！请重试！");
        }
        log.info("用户修改密码-成功：username={}",userName);
        return true;
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
        User newUser=userRepository.findByUserName(userFrom.getUserName());
        if (newUser!=null){
            BeanUtils.copyProperties(userFrom,newUser);
            throw new ConfException("用户名已经存在！");
        }
        else {
            newUser=new User();
            BeanUtils.copyProperties(userFrom,newUser);
            newUser.setUserId(KeyUtil.getUniqueKey());
            newUser.setStatus(UserStatusEnum.NEW_USER_STATUS.getCode());
            newUser.setPassword(BcryptEncoderUtil.toBcryptString(newUser.getPassword()));
            newUser.setCreateDate(new Date());
        }
        Role role=roleService.findByRoleName(RoleNameEnum.ROLE_USER.getRoleName());
        if (role==null){
            log.error("用户角色错误！");
            throw new ConfException("用户角色错误！");
        }
        UserRole userRole=new UserRole();
        userRole.setRoleId(role.getRoleId());
        userRole.setUserId(newUser.getUserId());
        try{
            userRole=userRoleService.saveOne(userRole);
        }
        catch (Exception e){
            log.error("用户-角色关系保存失败！");
            throw new ConfException("用户-角色关系保存失败！");
        }
        User re=null;
        try {
            re=userRepository.save(newUser);
        }catch (Exception e){
            log.error("用户信息保存失败！");
            throw new ConfException("用户信息保存失败！");
        }
        return re;
    }


    @Transactional(rollbackOn = Exception.class)
    public User userRegister2(UserFrom userFrom,String name) throws Exception {
        User old=userRepository.findByUserName(userFrom.getUserName());
        if (old!=null){
            throw new ConfException("用户名已经存在！");
        }
        User newUser=new User();
        BeanUtils.copyProperties(userFrom,newUser);
        newUser.setUserId(KeyUtil.getUniqueKey());
        newUser.setName(name);
        newUser.setStatus(UserStatusEnum.NEW_USER_STATUS.getCode());
        newUser.setPassword(BcryptEncoderUtil.toBcryptString(newUser.getPassword()));
        newUser.setCreateDate(new Date());

        Role role=roleService.findByRoleName(RoleNameEnum.ROLE_USER.getRoleName());
        if (role==null){
            log.error("用户角色错误！");
            throw new ConfException("用户角色错误！");
        }
        UserRole userRole=new UserRole();
        userRole.setRoleId(role.getRoleId());
        userRole.setUserId(newUser.getUserId());
        try{
            userRole=userRoleService.saveOne(userRole);
        }
        catch (Exception e){
            log.error("用户-角色关系保存失败！");
            throw new ConfException("用户-角色关系保存失败！");
        }
        User re=null;
        try {
            re=userRepository.save(newUser);
        }catch (Exception e){
            log.error("用户信息保存失败！");
            throw new ConfException("用户信息保存失败！");
        }
        return re;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public User addManager(AddManagerFrom from) throws Exception {
        if(from==null){
            return null;
        }
        User user=null;
        user=findByUserName(from.getUserName());
        if (user!=null){
            throw new ConfException("用户已经创建");
        }
        UserFrom userFrom=new UserFrom();
        BeanUtils.copyProperties(from,userFrom);
        try {
            user= userRegister(userFrom);
        }catch (Exception e){
            throw new ConfException(e.getMessage());
        }

        if (user==null){
            return null;
        }
        user.setName(from.getName());
        try {
            user= userRepository.save(user);
        }catch (Exception e){
            throw new ConfException("添加管理-用户保存失败");
        }
        ConferenceUer conferenceUer=new ConferenceUer();
        conferenceUer.setConferenceUserId(KeyUtil.getUniqueKey());
        BeanUtils.copyProperties(from,conferenceUer);
        try {
            conferenceUserService.saveOne(conferenceUer);
        }catch (Exception e){
            log.error("添加会议管理-会议-用户关系保存失败！e={}",e.getMessage());
            throw new ConfException("添加会议管理-会议-用户关系保存失败！");
        }
        return user;
    }

    @Override
    public List<User> findListByUserNameIn(List<String> userNameList) {
        return userRepository.findAllByUserNameIn(userNameList);
    }

    @Override
    public List<ApplicationManageVo> getPassedUserList(String confId) throws Exception {
        return applicationService.getApplicationManageVoByConfIdAndStatus(confId, ApplicationStatusEnum.APPLICATION_STATUS_PASSED);
    }

    @Override
    public List<UserVo> getVoListByConfIdAndRoleName(String confId, String roleName) throws Exception {
        List<ConferenceUer> conferenceUerList=conferenceUserService.findAllByConfIdAndRoleName(confId,roleName);
        if (conferenceUerList==null){
            log.warn("查找UserVo-cu为空");
            return null;
        }
        List<String> usernameList=new LinkedList<>();
        for (ConferenceUer conferenceUer:conferenceUerList){
            if (conferenceUer.getRoleName().equals(roleName)){
                usernameList.add(conferenceUer.getUserName());
            }
        }
        if (usernameList.isEmpty()){
            log.error("查找uservo-失败-不存在用户信息");
            return null;
        }
        List<User> userList=userRepository.findAllByUserNameIn(usernameList);

        List<UserVo> userVoList=new LinkedList<>();
        for (User user:userList){
            UserVo vo=new UserVo();
            BeanUtils.copyProperties(user,vo);
            userVoList.add(vo);
        }
        return userVoList;
    }

    @Override
    public void findPass(String userName, String email) throws Exception {
        //查询用户
        if (userName==null||email==null){
            log.error("用户找回密码-失败-参数错误：username={}，email={}",userName,email);
            throw new ConfException("错误的请求！");
        }
        User user=findByUserName(userName);
        if (user==null){
            log.error("用户找回密码-失败-用户信息不存在：userName={}",userName);
            throw new ConfException("用户名错误！");
        }
        if (user.getEmail()==null||!user.getEmail().equals(email)){
            log.error("用户找回密码-失败-邮箱错误：userName={},email={}",userName,email);
            throw new ConfException("未绑定邮箱！");
        }
        //生成密码
        String passStr=KeyUtil.getStr_6();
        log.info(passStr);
        //设置密码
        //发送邮件
        iMailService.sendSimpleMail(email,"学术会议管理系统-找回密码","请使用密码"+passStr+"登录修改密码！");
        //保存
        user.setPassword(BcryptEncoderUtil.toBcryptString(passStr));
        try {
            saveOne(user);
        }catch (Exception e){
            log.info(e.getMessage()+e.getCause()+e.getStackTrace());
            log.error("用户找回密码失败-信息存储失败-");
        }

    }

    /**
     * 查询专家列表
     * @param confId
     * @return
     * @throws Exception
     */
    public List<UserVo> getProfessorList(String confId) throws Exception{
        return getVoListByConfIdAndRoleName(confId,RoleNameEnum.ROLE_PROGESSOR.getRoleName());
    }


}
