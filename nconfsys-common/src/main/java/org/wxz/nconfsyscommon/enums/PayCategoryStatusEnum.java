package org.wxz.nconfsyscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wan
 * @create 2020/5/9 15:09
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PayCategoryStatusEnum {
    CATEGORY_STATUS_ENUM_NEW(0,"新建"),
    CATEGORY_STATUS_ENUM_PAYING(1,"缴费中"),
    CATEGORY_STATUS_ENUM_PAYEND(2,"缴费结束")

    ;
    private int code;

    private String mesage;

}
