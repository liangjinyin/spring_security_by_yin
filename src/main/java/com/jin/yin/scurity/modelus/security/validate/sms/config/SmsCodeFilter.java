package com.jin.yin.scurity.modelus.security.validate.sms.config;

import com.jin.yin.scurity.common.constants.constant.SystemConstant;
import com.jin.yin.scurity.common.interceptor.LoginHandler;
import com.jin.yin.scurity.modelus.security.validate.sms.message.SmsValidateProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: liangjinyin
 * @Date: 2018-09-03
 * @Description: OncePerRequestFilter 保证filter只会调用一次
 */
@Component
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Resource(name = "SmsValidateProcessor")
    private SmsValidateProcessor smsValidateProcessor;

    @Autowired
    private LoginHandler loginHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String ss =  request.getRequestURL().toString();

        /** 首先确定拦截路径url*/
        if (StringUtils.equals(SystemConstant.SMS_FILTER_URL, request.getRequestURL()) && StringUtils.equalsIgnoreCase("POST", request.getMethod())) {
            try {
                smsValidateProcessor.validate(new ServletWebRequest(request));
            } catch (AuthenticationException e) {
                loginHandler.onAuthenticationFailure(request,response,e);
            }
        } else {
            /** 不符合的拦截路径就向下执行*/
            chain.doFilter(request,response);
        }
    }
}
