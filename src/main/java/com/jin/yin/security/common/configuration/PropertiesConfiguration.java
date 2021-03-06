package com.jin.yin.security.common.configuration;

import com.jin.yin.security.common.constants.properties.SystemProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liangjinyin
 * @Date: 2018-08-22
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(SystemProperties.class)
public class PropertiesConfiguration {
}
