package com.jin.yin.scurity.modelus.security.social.qq.connect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @author: liangjinyin
 * @Date: 2018-08-29
 * @Description:
 */
@Slf4j
public class QOAuth2Template extends OAuth2Template {

    public QOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String response = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        log.info("获取的token返回是：{}",response);
        String[] temp = StringUtils.split(response, "&");

        String accessToken = StringUtils.substringAfterLast(temp[0], "=");
        Long expiresIn = new Long(StringUtils.substringAfterLast(temp[1], "="));
        String refreshToken = StringUtils.substringAfterLast(temp[2], "=");
        return new AccessGrant(accessToken, null, refreshToken, expiresIn);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));
        return restTemplate;
    }
}
