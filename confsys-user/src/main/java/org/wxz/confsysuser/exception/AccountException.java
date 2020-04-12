package org.wxz.confsysuser.exception;

import lombok.Getter;
import org.apache.shiro.authc.AuthenticationException;
import org.wxz.confsysuser.enums.AccountExceptionEnum;

/**
 * 账号异常
 * @Author xingze Wang
 * @create 2020/4/12 15:13
 */
@Getter
public class AccountException extends AuthenticationException {

    /**
     * 错误码
     */
    private Integer code;

    public AccountException(){ }

    public AccountException(String message){
        super(message);
    }

    public AccountException(Throwable cause){
        super(cause);
    }

    public AccountException(String message,Throwable cause){
        super(message,cause);
    }

    public AccountException(Integer code,String message){
          super(message);
          this.code=code;
    }

    public AccountException(AccountExceptionEnum exceptionEnum){
        super(exceptionEnum.getMessage());
        this.code=exceptionEnum.getCode();
    }

}
