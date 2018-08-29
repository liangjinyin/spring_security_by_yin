package com.jin.yin.scurity.modelus.security.qq.configuration;

import com.jin.yin.scurity.common.constants.properties.QQProperties;
import com.jin.yin.scurity.common.constants.properties.SystemProperties;
import com.jin.yin.scurity.modelus.security.qq.connect.QConnetionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author: liangjinyin
 * @Date: 2018-08-28
 * @Description:
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.system.social.qqps",name = "app-id")
public class QqConfiguration extends SocialAutoConfigurerAdapter{

    @Autowired
    private SystemProperties properties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = properties.getSocial().getQqps();
        return new QConnetionFactory(qqProperties.getProviderId(),qqProperties.getAppId(),qqProperties.getAppSecret());
    }
}
