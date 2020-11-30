package com.yu.security.authentication.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @Author: yy
 * @Date: 2020/11/30 14:59
 * @Version: 1.0.0
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg, Throwable t){
        super(msg,t);
    }

    public ValidateCodeException(String msg){
        super(msg);
    }
}
