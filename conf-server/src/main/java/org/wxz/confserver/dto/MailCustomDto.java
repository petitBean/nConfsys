package org.wxz.confserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wang
 * @create 2020/5/2 15:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailCustomDto {

    private String toMail;

    private String content;

    private String subject;
}
