package org.wxz.nconfsyscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wang
 * @create 2020/5/2 16:32
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum MailSubjectEnum {
    MAIL_SUBJECT_ENUM_COMPLETE_INFO(0,"学术会议管理系统-完善个人信息-邮箱验证"),
    MAIL_SUBJECT_ENUM_FORGETPASS(1,"学术会议管理系统-找回密码-邮箱验证"),

    ;
    private int code;

    private String subject;


}
