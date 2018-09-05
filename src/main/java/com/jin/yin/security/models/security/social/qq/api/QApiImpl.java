package com.jin.yin.security.models.security.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jin.yin.security.models.security.social.qq.entity.QUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @author: liangjinyin
 * @Date: 2018-08-28
 * @Description: qqapi的实现类
 */
@Slf4j
public class QApiImpl extends AbstractOAuth2ApiBinding implements QApi {

    public static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    public static final String URL_GET_QQ_USER = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;
    private String openId;

    private ObjectMapper mapper = new ObjectMapper();

    public QApiImpl(String accessToken, String appId) {

        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);

        String openid = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
        log.info("获取的openid为：{}", openid);

        this.openId = openid;
    }

    @Override
    public QUser getUserInfo() {
        QUser user = null;
        String url = String.format(URL_GET_QQ_USER, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        log.info("获取的qq对象是：{}",result);
        try {
            user =  mapper.readValue(result, QUser.class);
            user.setOpenId(openId);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取qq对象异常：{}",e.getMessage());
        }
        return user;
    }
}
