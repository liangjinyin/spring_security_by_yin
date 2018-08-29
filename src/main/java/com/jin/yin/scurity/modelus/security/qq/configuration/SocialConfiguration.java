package com.jin.yin.scurity.modelus.security.qq.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;


/**
 * @author: liangjinyin
 * @Date: 2018-08-28
 * @Description:
 */
@Configuration
@EnableSocial
public class SocialConfiguration extends SocialConfigurerAdapter {

    @Autowired
    private DataSource druidDataSource;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository connectionRepository = new JdbcUsersConnectionRepository(druidDataSource, connectionFactoryLocator, Encryptors.noOpText());
        connectionRepository.setTablePrefix("sys_");
        return connectionRepository;
    }

    @Bean
    public SpringSocialConfigurer springSocialConfigurer(){
        return new MySocialConfigurer();
    }
}
