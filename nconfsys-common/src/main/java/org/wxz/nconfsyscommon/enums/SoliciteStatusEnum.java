package org.wxz.nconfsyscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author xingze Wang
 * @create 2020/5/8 22:35
 */
@Getter
@AllArgsConstructor
public enum SoliciteStatusEnum {
    SOLICITE_STATUS_ENUM_CREATED(0,"创建完成"),
    SOLICITE_STATUS_ENUM_COMMENT(1,"评阅中"),
    SOLICITE_STATUS_ENUM_END(2,"评阅结束"),
    ;
    private int code;

    private String message;
}
