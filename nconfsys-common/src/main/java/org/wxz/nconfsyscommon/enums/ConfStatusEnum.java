package org.wxz.nconfsyscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author xingze Wang
 * @create 2020/4/23 16:34
 */
@AllArgsConstructor
@Getter
public enum ConfStatusEnum {
    NEW_CONF_STATUS(0,"待完善"),
    COMPLETED_DETAIL_STATUS(1,"创建完成"),
    PAPER_COLLECTINT_STATUS(2,"征稿中"),
    PAYING_CONF_STATUS(3,"缴费阶段"),
    HOLDING_STATUS(4,"正在举办"),
    COMPLETED_STATUS(5,"已经结束"),
    ;
    private int code;

    private String message;

    public static ConfStatusEnum getByCode(int code){
        for(ConfStatusEnum confStatusEnum:ConfStatusEnum.values()){
            if (confStatusEnum.getCode()==code){
                return confStatusEnum;
            }
        }
        return null;
    }

    public static ConfStatusEnum getByMsg(String msg){
        for(ConfStatusEnum confStatusEnum:ConfStatusEnum.values()){
            if (confStatusEnum.getMessage().equals(msg)){
                return confStatusEnum;
            }
        }
        return null;
    }


}
