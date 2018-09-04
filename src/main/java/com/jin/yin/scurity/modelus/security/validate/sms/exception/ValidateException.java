package com.jin.yin.scurity.modelus.security.validate.sms.exception;

import com.jin.yin.scurity.common.enums.ResultCode;
import org.springframework.security.core.AuthenticationException;

/**
 * @author: liangjinyin
 * @Date: 2018-08-31
 * @Description:
 */
public class ValidateException extends AuthenticationException {

    public ValidateException(String msg) {
        super(msg);
        ResultCode.createCustomResultCode(msg);
    }
}
