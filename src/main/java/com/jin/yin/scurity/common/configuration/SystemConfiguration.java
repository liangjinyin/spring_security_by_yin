package com.jin.yin.scurity.common.configuration;

import com.jin.yin.scurity.common.interceptor.ParamInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author: liangjinyin
 * @Date: 2018-08-22
 * @Description: 系统的配置
 */
//@Configuration
public class SystemConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private ParamInterceptor paramInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        /** 自定义一个拦截器拦截所有的url并用日志记录url的参数信息*/
        /*registry.addInterceptor(paramInterceptor)
                //.excludePathPatterns("/*.html")
                .addPathPatterns("/**");*/
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
