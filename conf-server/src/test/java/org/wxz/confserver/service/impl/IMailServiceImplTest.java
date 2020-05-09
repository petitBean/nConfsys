package org.wxz.confserver.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confserver.dto.MailCustomDto;
import org.wxz.nconfsyscommon.enums.MailSubjectEnum;
import org.wxz.nconfsyscommon.utils.KeyUtil;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/5/2 1:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class IMailServiceImplTest {

    @Autowired
    private IMailServiceImpl iMailService;

    @Test
    void sendSimpleMail() {
        iMailService.sendSimpleMail("841246785@qq.com","主题1","内容c");
    }

    @Test
    void sendHtmlMail() {
    }

    @Test
    void sendAttachmentsMail() {
    }

    @Test
    void getCode() throws Exception{
        MailCustomDto customDto=new MailCustomDto();
        customDto.setToMail("841246785@qq.com");
        customDto.setSubject(MailSubjectEnum.MAIL_SUBJECT_ENUM_COMPLETE_INFO.getSubject());
        customDto.setContent(KeyUtil.getIdentiCode());
        iMailService.getIdentiCode("13720131232",customDto);
    }

}