package org.wxz.nconfsyscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author xingze Wang
 * @create 2020/4/29 0:28
 */
@Getter
@AllArgsConstructor
public enum ApplicationStatusEnum {

    APPLICATION_STATUS_NEW(0,"待审核"),
    APPLICATION_STATUS_PASSED(1,"已经通过"),
    APPLICATION_STATUS_REFUSED(2,"未通过"),
    ;
    private  int code;

    private String message;

    public static ApplicationStatusEnum getByCode(int code){
        for (ApplicationStatusEnum statusEnum:ApplicationStatusEnum.values()){
            if (statusEnum.getCode()==code){
                return statusEnum;
            }
        }
        return null;
    }

    public static ApplicationStatusEnum getByMsg(String message){
        for (ApplicationStatusEnum statusEnum:ApplicationStatusEnum.values()){
            if (message.equals(statusEnum.getMessage())){
                return statusEnum;
            }
        }
        return null;
    }

}
