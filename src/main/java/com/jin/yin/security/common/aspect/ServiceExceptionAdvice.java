package com.jin.yin.security.common.aspect;

import com.jin.yin.security.common.enums.ResultCode;
import com.jin.yin.security.common.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: liangjinyin
 * @Date: 2018-09-10
 * @Description:
 */
@ControllerAdvice(basePackages = "com.jin.yin.security.models.*.service")
public class ServiceExceptionAdvice {

    @ExceptionHandler(ServiceException.class)/** 此处可以加自定义异常*/
    @ResponseBody
    public ResponseEntity handleException(ServiceException e){
        Map<String, Object> map = new HashMap(8);
        map.put("message", e.getMessage());
        map.put("code",e.getCode());
        return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}