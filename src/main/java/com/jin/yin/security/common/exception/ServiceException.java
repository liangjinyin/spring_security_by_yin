package com.jin.yin.security.common.exception;

import com.jin.yin.security.common.enums.ResultCode;
import lombok.Data;

/**
 * @author: liangjinyin
 * @Date: 2018-09-10
 * @Description: 统一处理service层的exception类
 */
@Data
public class ServiceException extends RuntimeException {

    private String code;

    public ServiceException(String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(ResultCode resultCode) {
        super(resultCode.getResultMsg());
        this.code = code;
    }
}
