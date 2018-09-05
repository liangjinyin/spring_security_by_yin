package com.jin.yin.security.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;

/**
 * @author: liangjinyin
 * @Date: 2018-09-05
 * @Description:
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 10*60)
public class SessionConfiguration {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate() ;
    }
}
