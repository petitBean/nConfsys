package org.wxz.confserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wxz.confserver.dto.MailCustomDto;
import org.wxz.confserver.service.UserService;
import org.wxz.confserver.service.impl.IMailServiceImpl;
import org.wxz.confsysdomain.nconfsysuser.User;
import org.wxz.nconfsyscommon.enums.MailSubjectEnum;
import org.wxz.nconfsyscommon.resultVO.ConfResponse;
import org.wxz.nconfsyscommon.utils.KeyUtil;

/**
 * @Author xingze Wang
 * @create 2020/5/2 15:51
 */
@RestController
@Slf4j
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private IMailServiceImpl mailService;

    @Autowired
    private UserService userService;

    @GetMapping("/get_identicode")
    public ConfResponse getIdentiCode(@RequestParam(value = "username",required = true)String userName,
                                      @RequestParam(value="to",required = true)String to){
        if (userName==null){
            log.error("用户获取验证码-错误-用户为空：username={}",userName);
            return ConfResponse.fail("系统异常，重新登录！");
        }
        User user=userService.findByUserName(userName);
        if (user==null){
            log.error("用户获取验证码-错误-用户为空：username={}",userName);
            return ConfResponse.fail("系统异常，重新登录！");

        }
        String  code=KeyUtil.getIdentiCode();
        MailCustomDto customDto=new MailCustomDto();
        customDto.setToMail(to);
        customDto.setSubject(MailSubjectEnum.MAIL_SUBJECT_ENUM_COMPLETE_INFO.getSubject());
        customDto.setContent(code);
        log.info("用户获取验证码-dto={}",customDto);
        try {
            mailService.getIdentiCode(userName,customDto);
        }catch (Exception e){
            log.error("获取验证码-异常：e={}",e.getMessage()+"\n"+e.getStackTrace());
            return ConfResponse.fail(e.getMessage());
        }
        return ConfResponse.success();
    }

}
