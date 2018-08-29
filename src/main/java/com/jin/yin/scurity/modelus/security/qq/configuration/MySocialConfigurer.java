package com.jin.yin.scurity.modelus.security.qq.configuration;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author: liangjinyin
 * @Date: 2018-08-28
 * @Description:
 */
@Component
public class MySocialConfigurer extends SpringSocialConfigurer {

    public static final String URL_QQ_SEND_TOKEN = "/login";

    /**
     * 重写postProcess方法，设置qq发送token的回调的url
     * @param object
     * @param <T>
     * @return
     */
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter)super.postProcess(object);
        filter.setFilterProcessesUrl(URL_QQ_SEND_TOKEN);
        return (T) filter;
    }
}
