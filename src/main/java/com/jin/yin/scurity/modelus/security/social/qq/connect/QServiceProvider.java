package com.jin.yin.scurity.modelus.security.social.qq.connect;

import com.jin.yin.scurity.modelus.security.social.qq.api.QApi;
import com.jin.yin.scurity.modelus.security.social.qq.api.QApiImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @author: liangjinyin
 * @Date: 2018-08-28
 * @Description:
 */
public class QServiceProvider extends AbstractOAuth2ServiceProvider<QApi>{

    private String appId;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QServiceProvider(String appId,String appSecret) {
        super(new QOAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QApi getApi(String accessToken) {
        return new QApiImpl(accessToken,appId);
    }
}
