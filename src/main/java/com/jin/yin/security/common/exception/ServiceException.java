package com.jin.yin.security.common.exception;

/**
 * @author: liangjinyin
 * @Date: 2018-09-10
 * @Description: 统一处理service层的exception类
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }
}
