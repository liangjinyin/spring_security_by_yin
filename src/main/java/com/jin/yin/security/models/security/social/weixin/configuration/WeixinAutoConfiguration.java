/**
 * 
 */
package com.jin.yin.security.models.security.social.weixin.configuration;

import com.jin.yin.security.common.constants.properties.SystemProperties;
import com.jin.yin.security.common.constants.properties.WXProperty;
import com.jin.yin.security.models.security.social.weixin.connect.WeixinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * 微信登录配置
 * 
 * @author zhailiang
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.system.social.wxps", name = "app-id")
public class WeixinAutoConfiguration extends SocialAutoConfigurerAdapter {

	@Autowired
	private SystemProperties properties;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		WXProperty weixinConfig = properties.getSocial().getWxps();
		return new WeixinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
				weixinConfig.getAppSecret());
	}
	
	/*@Bean({"connect/weixinConnect", "connect/weixinConnected"})
	@ConditionalOnMissingBean(name = "weixinConnectedView")
	public View weixinConnectedView() {
		return new ImoocConnectView();
	}*/
	
}
