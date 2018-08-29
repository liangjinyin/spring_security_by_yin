package com.jin.yin.scurity.modelus.security.qq.connect;

import com.jin.yin.scurity.modelus.security.qq.api.QApi;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author: liangjinyin
 * @Date: 2018-08-28
 * @Description:
 */
public class QConnetionFactory extends OAuth2ConnectionFactory<QApi> {

    public QConnetionFactory(String providerId,String appId,String appSecret ) {
        super(providerId, new QServiceProvider(appId,appSecret), new QAdaptor());
    }
}
