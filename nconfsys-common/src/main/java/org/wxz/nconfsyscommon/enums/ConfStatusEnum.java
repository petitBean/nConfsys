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
    NEW_CONF_STATUS(0,"新创建的会议"),
    COMPLETED_DETAIL_STATUS(1,"已经完成会议详情创建"),
    PAPER_COLLECTINT_STATUS(2,"征稿中"),
    PAYING_CONF_STATUS(3,"缴费阶段"),
    HOLDING_STATUS(4,"正在举办"),
    COMPLETED_STATUS(5,"已经结束"),
    ;
    private int code;

    private String message;


}
