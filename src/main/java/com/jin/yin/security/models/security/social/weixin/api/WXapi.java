package com.jin.yin.security.models.security.social.weixin.api;

import com.jin.yin.security.models.security.social.weixin.entity.WeiXinUser;

/**
 * @author: liangjinyin
 * @Date: 2018-08-29
 * @Description:
 */
public interface WXapi {

    WeiXinUser getUserInfo(String openId);
}
