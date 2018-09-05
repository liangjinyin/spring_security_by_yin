package com.jin.yin.security.models.security.social.qq.connect;

import com.jin.yin.security.models.security.social.qq.api.QApi;
import com.jin.yin.security.models.security.social.qq.entity.QUser;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author: liangjinyin
 * @Date: 2018-08-28
 * @Description:
 */
public class QAdaptor implements ApiAdapter<QApi> {

    @Override
    public boolean test(QApi api) {
        /**
         * 测试qq是否连接通
         */
        return true;
    }

    @Override
    public void setConnectionValues(QApi api, ConnectionValues connectionValues) {
        QUser userInfo = api.getUserInfo();
        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setImageUrl(userInfo.getFigureurl_2());
        connectionValues.setProfileUrl(null);
        connectionValues.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QApi api) {
        return null;
    }

    @Override
    public void updateStatus(QApi api, String message) {
        /**
         * 微博等应用采用
         */
    }
}
