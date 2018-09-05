package com.jin.yin.security.common.constants.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: liangjinyin
 * @Date: 2018-08-22
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "spring.system")
public class SystemProperties {

    private DruidProperties druid = new DruidProperties();
    private CorsProperties cors = new CorsProperties();
    private SocialProperty social = new SocialProperty();
    private ValidateProperty validate = new ValidateProperty();
}
