package org.wxz.confsysuser.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author xingze Wang
 * @create 2020/4/12 15:04
 */
@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public String authorizationExceptionHandler(HttpServletRequest request, HttpServletResponse response){
        return "对不起，您没有操作权限！";
    }


    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public String authenticationExceptionHandler(HttpServletRequest request,HttpServletResponse response){
        return "认证不通过！";
    }



}
