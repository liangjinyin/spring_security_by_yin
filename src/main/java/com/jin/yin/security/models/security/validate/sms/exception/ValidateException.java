package com.jin.yin.security.models.security.validate.sms.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author: liangjinyin
 * @Date: 2018-08-31
 * @Description:
 */
public class ValidateException extends AuthenticationException {

    public ValidateException(String msg) {
        super(msg);
    }
}
