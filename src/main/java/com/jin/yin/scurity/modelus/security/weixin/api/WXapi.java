package com.jin.yin.scurity.modelus.security.weixin.api;

import com.jin.yin.scurity.modelus.security.weixin.entity.WeiXinUser;

/**
 * @author: liangjinyin
 * @Date: 2018-08-29
 * @Description:
 */
public interface WXapi {

    WeiXinUser getUserInfo(String openId);
}
