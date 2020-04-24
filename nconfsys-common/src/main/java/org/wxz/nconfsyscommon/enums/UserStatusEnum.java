package org.wxz.nconfsyscommon.enums;

import lombok.Getter;

/**
 * @Author xingze Wang
 * @create 2020/4/22 12:51
 */
@Getter
public enum UserStatusEnum {

    NEW_USER_STATUS(0,"注册完成的新用户"),
    COMPLETE_USER_STATUS(1,"已经完善信息的用户"),


    ;
    private int code;

    private String message;

    UserStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
