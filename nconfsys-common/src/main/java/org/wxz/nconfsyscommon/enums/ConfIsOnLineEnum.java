package org.wxz.nconfsyscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author xingze Wang
 * @create 2020/4/23 16:43
 */
@Getter
@AllArgsConstructor
public enum ConfIsOnLineEnum {
    IS_ONLINE(0,"支持"),
    NO_ONLINE(1,"不支持"),
    ;
    private int code;
    private String message;

    public static ConfIsOnLineEnum getByCode(int code){
        for(ConfIsOnLineEnum confIsOnLineEnum:ConfIsOnLineEnum.values()){
            if (confIsOnLineEnum.getCode()==code){
                return confIsOnLineEnum;
            }
        }
        return null;
    }

    public static ConfIsOnLineEnum getByMsg(String msg){
        for(ConfIsOnLineEnum confIsOnLineEnum:ConfIsOnLineEnum.values()){
            if (confIsOnLineEnum.getMessage().equals(msg)){
                return confIsOnLineEnum;
            }
        }
        return null;
    }

}
