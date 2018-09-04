package com.jin.yin.scurity.modelus.security.social.qq.entity;

import lombok.Data;

/**
 * @author: liangjinyin
 * @Date: 2018-08-29
 * @Description: 当用户用第三方社交登陆时，返回给前端的展示（昵称，头像等）
 */
@Data
public class SocialUserInfo {

    /** 第三方的id*/
    private String providerId;
    /** 自己服务的user唯一标识*/
    private String providerUserId;
    /** 昵称*/
    private String nickname;
    /** 头像*/
    private String headimg;
}
