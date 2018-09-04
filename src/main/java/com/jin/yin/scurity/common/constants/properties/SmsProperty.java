package com.jin.yin.scurity.common.constants.properties;

import lombok.Data;

/**
 * @author: liangjinyin
 * @Date: 2018-08-31
 * @Description:
 */
@Data
public class SmsProperty {

    /** 短信验证码默认长度*/
    private Integer slength = 4;
    /** 短信验证码的有效时间*/
    private Integer expireTime = 10*60;
}
