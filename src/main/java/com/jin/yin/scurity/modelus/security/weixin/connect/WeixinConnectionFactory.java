/**
 * 
 */
package com.jin.yin.scurity.modelus.security.weixin.connect;


import com.jin.yin.scurity.modelus.security.weixin.api.WXapi;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * 微信连接工厂
 * 
 * @author zhailiang
 *
 */
public class WeixinConnectionFactory extends OAuth2ConnectionFactory<WXapi> {
	
	/**
	 * @param appId
	 * @param appSecret
	 */
	public WeixinConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new WeixinServiceProvider(appId, appSecret), new WeixinAdapter());
	}
	
	/**
	 * 由于微信的openId是和accessToken一起返回的，所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取
	 */
	@Override
	protected String extractProviderUserId(AccessGrant accessGrant) {
		if(accessGrant instanceof WeixinAccessGrant) {
			return ((WeixinAccessGrant)accessGrant).getOpenId();
		}
		return null;
	}

	@Override
	public Connection<WXapi> createConnection(AccessGrant accessGrant) {
		return new OAuth2Connection<WXapi>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
				accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
	}


	@Override
	public Connection<WXapi> createConnection(ConnectionData data) {
		return new OAuth2Connection<WXapi>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
	}
	
	private ApiAdapter<WXapi> getApiAdapter(String providerUserId) {
		return new WeixinAdapter(providerUserId);
	}
	
	private OAuth2ServiceProvider<WXapi> getOAuth2ServiceProvider() {
		return (OAuth2ServiceProvider<WXapi>) getServiceProvider();
	}

	
}
