package com.jin.yin.scurity.modelus.security.weixin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jin.yin.scurity.modelus.security.weixin.entity.WeiXinUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author: liangjinyin
 * @Date: 2018-08-29
 * @Description:
 */
@Slf4j
public class WXapiImpl extends AbstractOAuth2ApiBinding implements WXapi {

    /**
     * 获取用户信息的url
     */
    private static final String WEIXIN_URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=%s";

    private ObjectMapper objectMapper = new ObjectMapper();

    public WXapiImpl(String accessToken) {
        super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    @Override
    public WeiXinUser getUserInfo(String openId) {
        String url = String.format(WEIXIN_URL_GET_USER_INFO, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        boolean flag = StringUtils.contains(result, "errcode");
        if (flag){
            log.warn("获取的微信用户信息异常：{}",result);
            return null;
        }
        log.info("获取的微信用户信息是：{}",result);
        WeiXinUser weiXinUser = null;
        try {
            weiXinUser = objectMapper.readValue(result, WeiXinUser.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.warn("获取的微信用户信息异常：{}",e.getMessage());
        }
        return weiXinUser;
    }

    /**
     * 使用utf-8 替换默认的ISO-8859-1编码
     * @return
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }
}
