package org.wxz.nconfsyscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * @Author xingze Wang
 * @create 2020/5/9 15:13
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OrderStatusEnum {

    ORDER_STATUS_ENUM_NEW(0,"新建"),
    ORDER_STATUS_ENUM_PAYED(2,"结束"),
    ORDER_STATUS_ENUM_CANCEL(1,"已经取消"),
    ;
  private int code;

  private String message;


}
