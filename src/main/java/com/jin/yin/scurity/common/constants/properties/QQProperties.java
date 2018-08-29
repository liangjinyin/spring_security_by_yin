package com.jin.yin.scurity.common.constants.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author: liangjinyin
 * @Date: 2018-08-28
 * @Description:
 */
@Data
public class QQProperties extends SocialProperties {

    private String providerId = "qq";
}
