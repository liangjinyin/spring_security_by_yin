package com.jin.yin.security.models.security.validate.sms.message;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: liangjinyin
 * @Date: 2018-08-31
 * @Description: 短信验证码
 */
@Data
public class ValidateCode {

    /** 验证码*/
    private String code;
    /** 过期时间*/
    private LocalDateTime expireTime;

    public ValidateCode(String code, Integer expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }
    /** 是否过期*/
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
