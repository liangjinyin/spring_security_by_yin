package com.jin.yin.scurity.modelus.security.social.qq.connect;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @author: liangjinyin
 * @Date: 2018-08-29
 * @Description:
 */
@Component
public class QconnectionSignup implements ConnectionSignUp{

    @Override
    public String execute(Connection<?> connection) {
        //根据需求return 唯一标识
        return connection.getDisplayName();
    }
}
