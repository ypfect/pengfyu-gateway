package com.pengfyu.zuul.common;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author stanley.yu
 * @Description
 * @Date 2019/1/21 21:44
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}