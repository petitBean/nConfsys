package org.wxz.confserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.wxz.confserver.dto.MailCustomDto;
import org.wxz.confserver.service.IMailService;
import org.wxz.confsysdomain.common.IdentCode;
import org.wxz.confsysdomain.nconfsysconf.Application;
import org.wxz.confsysdomain.nconfsysconf.Conference;
import org.wxz.confsysdomain.nconfsysuser.User;
import org.wxz.confsysdomain.relation.ConferenceUer;
import org.wxz.nconfsyscommon.exception.ConfException;
import org.wxz.nconfsyscommon.utils.KeyUtil;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/2 1:34
 */
@Service
@Slf4j
public class IMailServiceImpl implements IMailService {

    /**
     * Spring Boot 提供了一个发送邮件的简单抽象，使用的是下面这个接口，这里直接注入即可使用
     */
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private IdentCodeServiceImpl identCodeService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ConferenceServiceImpl conferenceService;

    @Autowired
    private ApplicationServiceImpl applicationService;

    @Autowired
    private ConferenceUserServiceImpl conferenceUserService;

    /**
     * 配置文件中我的qq邮箱
     */
    @Value("${spring.mail.from}")
    private String from;

    /**
     * 简单文本邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        //创建SimpleMailMessage对象
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件发送人
        message.setFrom(from);
        //邮件接收人
        message.setTo(to);
        //邮件主题
        message.setSubject(subject);
        //邮件内容
        message.setText(content);
        //发送邮件
        try {
            mailSender.send(message);
        }catch (Exception e){
            log.error("发送邮件-失败：email={}",to);
        }

    }

    /**
     * html邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        //获取MimeMessage对象
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(from);
            //邮件接收人
            messageHelper.setTo(subject);
            //邮件主题
            message.setSubject(subject);
            //邮件内容，html格式
            messageHelper.setText(content, true);
            //发送
            mailSender.send(message);
            //日志信息
            log.info("邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常！", e);
        }
    }

    /**
     * 带附件的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            //日志信息
            log.info("邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常！", e);
        }


    }

    @Override
    public void sendMailToOne(MailCustomDto mailCustomDto) throws Exception {
        try {
            sendSimpleMail(mailCustomDto);
        }catch (Exception e){
            log.error("发送邮件-失败-dto={}",mailCustomDto);
            throw new ConfException("邮件发送失败！检查邮箱重试");
        }
    }

    @Override
    public void sendMailToList(List<MailCustomDto> mailCustomDtoList) throws Exception {
         for (MailCustomDto mailCustomDto:mailCustomDtoList){
             sendSimpleMail(mailCustomDto);
         }
    }

    @Override
    public void sendSimpleMail(MailCustomDto mailCustomDto) throws Exception {
        try {
            sendSimpleMail(mailCustomDto.getToMail(),mailCustomDto.getSubject(),mailCustomDto.getContent());
        }catch (Exception e){
            log.error("发送邮件-失败-dto={}",mailCustomDto);
            System.out.println(e.getCause()+e.getMessage()+e.getStackTrace());
            throw new ConfException("邮件发送失败！检查邮箱重试");
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void getIdentiCode(String userName,MailCustomDto customDto) throws Exception {
        IdentCode  identCode=new IdentCode();
        identCode.setId(KeyUtil.getUniqueKey());
        identCode.setUserName(userName);
        identCode.setCode(customDto.getContent());
        identCode.setCreateDate(new Date());
        identCodeService.deleteAllByUserName(userName);
        try {
            identCodeService.saveOne(identCode);
            sendSimpleMail(customDto);
        }catch (Exception e){
            log.error("用户获取验证码-失败-验证码存储失败：code={},userName={}",identCode,userName);
            throw new ConfException("发送失败！请重试");
        }
        //邮箱发送

    }

    public void sendNotice(String content,String confId,int range){
        List<User> userList=null;
        List<String> usernameList=null;
        //1全部工作人员
        if (range==1){
            List<ConferenceUer> conferenceUerList=conferenceUserService.findAllByConfId(confId);
            if (conferenceUerList==null){
                return;
            }
           for (ConferenceUer conferenceUer:conferenceUerList){
               usernameList.add(conferenceUer.getUserName());
           }
        }
        //2全部普通用户
        else if (range==2){
            List<Application> applicationList=applicationService.findListByConfId(confId);
            if (applicationList==null){
                return;
            }
            for (Application application:applicationList){
                usernameList.add(application.getUserName());
            }
        }
        else {
            List<ConferenceUer> conferenceUerList=conferenceUserService.findAllByConfId(confId);
            if (conferenceUerList!=null){
                for (ConferenceUer conferenceUer:conferenceUerList){
                    usernameList.add(conferenceUer.getUserName());
                }
            }

            List<Application> applicationList=applicationService.findListByConfId(confId);
            if (applicationList!=null){
                for (Application application:applicationList){
                    usernameList.add(application.getUserName());
                }
            }
        }
        if (usernameList==null){
            return;
        }
        userList=userService.findListByUserNameIn(usernameList);
        if (userList==null){
            return;
        }
        Conference conference=conferenceService.findOneByConfId(confId);
        if (conference==null){
            return;
        }
       /* for (User user:userList){
            if (user.getEmail()!=null){

            }
            sendSimpleMail(user.getEmail(),"学术会议管理系统"+conference.getConfName()+"通知:",content);
        }*/
        sendSimpleMail("841246785@qq.com","学术会议管理系统"+conference.getConfName()+"通知:",content);


    }

}
