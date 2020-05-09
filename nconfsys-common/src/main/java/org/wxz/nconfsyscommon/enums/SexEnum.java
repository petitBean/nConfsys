package org.wxz.nconfsyscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wang
 * @create 2020/5/2 17:03
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SexEnum {
    SEX_ENUM_MALE(0,"male"),
    SEX_ENUM_FEMALE(1,"female"),
    ;
    private int code;

    private String message;

    public static SexEnum getByMsg(String message){
        for (SexEnum sexEnum:SexEnum.values()){
            if (sexEnum.message.equals(message)){
                return sexEnum;
            }
        }
        return null;
    }

}
