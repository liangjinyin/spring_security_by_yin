package com.jin.yin.scurity.common.interceptor;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: liangjinyin
 * @Date: 2018-08-22
 * @Description:
 */
@Slf4j
@Component
public class ParamInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("request url={}, params={}, queryString={}",request.getRequestURI(), JSON.toJSONString(request.getParameterMap()),request.getQueryString());
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        if (ex != null) {
            log.error("处理请求url:{},出现{}异常",request.getRequestURI(),ex.getMessage());
        }
    }
}
