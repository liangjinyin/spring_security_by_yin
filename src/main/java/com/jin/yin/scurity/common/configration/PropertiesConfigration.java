package com.jin.yin.scurity.common.configration;

import com.jin.yin.scurity.common.constants.properties.SystemProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liangjinyin
 * @Date: 2018-08-22
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(SystemProperties.class)
public class PropertiesConfigration {
}
