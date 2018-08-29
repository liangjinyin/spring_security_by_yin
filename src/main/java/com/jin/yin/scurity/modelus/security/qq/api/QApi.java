package com.jin.yin.scurity.modelus.security.qq.api;

import com.jin.yin.scurity.modelus.security.qq.entity.QUser;

/**
 * @author: liangjinyin
 * @Date: 2018-08-28
 * @Description: qq api 接口
 */
public interface QApi {
    /**
     * 获取qq用户信息
     * @return
     */
    QUser getUserInfo();
}
