package org.wxz.confserver.service;

import org.wxz.confserver.dto.MailCustomDto;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/2 1:28
 */
public interface IMailService {

    /**
     * 发送文本邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送HTML邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendHtmlMail(String to, String subject, String content);



    /**
     * 发送带附件的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);


    void sendMailToOne(MailCustomDto mailCustomDto) throws Exception;

    void sendMailToList(List<MailCustomDto> mailCustomDtoList) throws Exception;

    void sendSimpleMail(MailCustomDto mailCustomDto)throws Exception;

    void getIdentiCode(String userName,MailCustomDto customDto)throws Exception;

}
