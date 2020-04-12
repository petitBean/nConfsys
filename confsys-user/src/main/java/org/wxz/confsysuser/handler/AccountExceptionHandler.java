package org.wxz.confsysuser.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wxz.confsysuser.exception.AccountException;
import org.wxz.nconfsyscommon.resultVO.ConfResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author xingze Wang
 * @create 2020/4/12 16:15
 */
@ControllerAdvice
public class AccountExceptionHandler {

    /**
     * 捕获账号异常
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(AccountException.class)
    public ConfResponse accountExceptionHandler(HttpServletRequest request,
                                                HttpServletResponse response,
                                                AccountException e){
         return ConfResponse.newConfResponse(e.getCode(),e.getMessage(),e);
    }

}
