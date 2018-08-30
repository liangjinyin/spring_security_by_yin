package com.jin.yin.scurity.common.constants.properties;

import lombok.Data;

/**
 * @author: liangjinyin
 * @Date: 2018-08-28
 * @Description:
 */
@Data
public class SocialProperty {

    private QQProperties qqps = new QQProperties();

    private WXProperty wxps = new WXProperty();
}
