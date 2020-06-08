package org.wxz.confserver.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wxz.confserver.from.AddManagerFrom;
import org.wxz.confserver.from.CompleteUserInfoFrom;
import org.wxz.confserver.service.impl.UserServiceImpl;
import org.wxz.confserver.vo.ApplicationManageVo;
import org.wxz.confsysdomain.nconfsysuser.User;
import org.wxz.nconfsyscommon.resultVO.ConfResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Author xingze Wang
 * @create 2020/5/2 16:45
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UersController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/completeUserInfo")
    public ConfResponse completeUserInfo(@RequestBody CompleteUserInfoFrom from){
        try {
            userService.completeInfo(from);
        }catch (Exception e){
           log.error("用户完善信息-失败：from={},e={}",from,e.getMessage()+e.getStackTrace());
           return ConfResponse.fail(e.getMessage());
        }
        log.info("用户完善信息成功！");
       return ConfResponse.success();
    }



    @PostMapping("/changePass")
    public ConfResponse changePass(@RequestParam(value = "username",required = true) String userName,
                                   @RequestParam(value = "password",required = true)String password,
                                   @RequestParam(value = "newPassword")String newPassword){
        log.info(userName+password+newPassword);
        try {
            userService.changePass(userName,password,newPassword);
        }catch (Exception e){
            log.error("用户修改密码-失败:exception={}",e.getMessage());
            return ConfResponse.fail(e.getMessage());
        }
        log.info("用户修改密码-成功：username={}",userName);
         return ConfResponse.success();
    }


    /**
     * 添加工作人员
     * @param addManagerForm
     * @return
     */
    @PostMapping("/addManager")
    public ConfResponse addManager(@RequestBody AddManagerFrom addManagerForm){
        if (addManagerForm==null){
            return ConfResponse.fail("参数错误！");
        }
        try {
            userService.addManager(addManagerForm);
        }catch (Exception e){
            log.error("添加会议管理工作人员-失败：e={}",e.getStackTrace().toString());
            return ConfResponse.fail(e.getMessage());
        }
        log.error("添加会议管理工作人员-成功");
        return ConfResponse.success();
    }


    //获取通过审核得用户列表
    @GetMapping("/getPassedUserList")
    public ConfResponse getPassedUserList(@RequestParam(value = "confId",required = true)String confId){
        if(confId==null){
            log.error("获取参会用户列表-错误-参数confId空！");
            return ConfResponse.fail("系统异常！");
        }
        List<ApplicationManageVo>  voList=null;
        try {
            voList=userService.getPassedUserList(confId);
        }catch (Exception e){
            log.error("获取参会用户列表-错误:e={}",e.getMessage()+'\n'+e.getStackTrace());
            return ConfResponse.fail("系统异常！");
        }
        return ConfResponse.success(voList);
    }

    /**
     * 用户找回密码
     * @param userName
     * @param email
     * @return
     */
    @PostMapping("/findpassword")
    public ConfResponse findPass(@RequestParam(value = "userName",required = true)String userName,
                                 @RequestParam(value = "email",required = true)String email){
        log.info(userName+email);
        if (userName==null||email==null){
            log.error("用户找回密码-参数错误：userName={},email={}",userName,email);
            return ConfResponse.fail("错误的请求！");
        }
        try {
          userService.findPass(userName,email);
        }catch (Exception e){
            log.error("用户找回密码失败-异常：e={}",e.getMessage());
            return ConfResponse.fail(e.getMessage());
        }
        log.info("用户找回密码-成功");
        return ConfResponse.success();
    }




}
