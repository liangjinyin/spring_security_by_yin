package com.jin.yin.security.common.constants.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author: liangjinyin
 * @Date: 2018-08-29
 * @Description:
 */
@Data
public class WXProperty extends SocialProperties {

    private String providerId = "weixin";

}
