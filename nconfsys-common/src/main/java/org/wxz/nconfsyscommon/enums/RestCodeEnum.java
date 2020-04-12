package org.wxz.nconfsyscommon.enums;

import lombok.Getter;

/**
 * 返回给前端的状态码
 * @Author xingze Wang
 * @create 2020/4/12 16:39
 */
@Getter
public enum RestCodeEnum {

    SUCCESS(200,"成功！"),
    FAILED(201,"失败！"),
    ERROR(500,"错误！"),
    ;
    private Integer code;
    private String message;

    RestCodeEnum(Integer code, String message){
        this.code=code;
        this.message=message;
    }

}
