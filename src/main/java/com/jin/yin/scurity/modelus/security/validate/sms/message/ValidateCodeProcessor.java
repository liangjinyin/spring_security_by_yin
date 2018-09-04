package com.jin.yin.scurity.modelus.security.validate.sms.message;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: liangjinyin
 * @Date: 2018-08-31
 * @Description: 验证码处理器接口
 */
public interface ValidateCodeProcessor {

    /**
     * 创建校验码
     *
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest
     * @throws Exception
     */
    void validate(ServletWebRequest servletWebRequest);
}
