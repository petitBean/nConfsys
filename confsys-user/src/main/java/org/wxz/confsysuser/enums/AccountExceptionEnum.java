package org.wxz.confsysuser.enums;

import lombok.Getter;

/**
 * 账号异常信息枚举
 * @Author xingze Wang
 * @create 2020/4/12 15:30
 */
@Getter
public enum AccountExceptionEnum {
    ACCOUNT_PASSWORD_ERROR(1001,"密码错误！"),
    ACCOUNT_USERNAME_ERROR(1002,"用户名错误！"),
    ACCOUNT_PASSWORD_EMPTY(1003,"密码为空！"),
    ACCOUNT_USERNAME_EMPTY(1004,"用户名为空！"),
    ACCOUNT_HAS_NO_PERMISSION(1005,"没有权限！"),
    ACCOUNT_NO_LOGIN(1006,"未登录！"),
    ACCOUNT_AUTHENTICAT_FAILED(1007,"认证失败！"),
    ACCOUNT_AUTHORIZE_FAILED(1008,"授权失败！"),
    ;

  private Integer code;
  private String message;

   AccountExceptionEnum(Integer code,String message){
       this.code=code;
       this.message=message;
   }

}
