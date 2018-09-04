package com.jin.yin.scurity.modelus.security.validate.sms.config;

import com.jin.yin.scurity.modelus.system.user.service.UserDetailsServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author: liangjinyin
 * @Date: 2018-09-03
 * @Description:
 */
@Slf4j
@Data
public class SmsAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 真正的验证逻辑
     * @param authentication SmsAuthenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken smsAuthenticationToken = (SmsAuthenticationToken)authentication;
        String mobile = (String) smsAuthenticationToken.getPrincipal();
        UserDetails user = userDetailsService.loadUserByUsername(mobile);
        if (user == null) {
            log.info("无法根据手机号获取用户信息，手机号为：{}",mobile);
            throw new InternalAuthenticationServiceException("无法根据手机号获取用户信息，手机号为："+mobile);
        }
        /** 将用户设置为已经验证通过*/
        SmsAuthenticationToken authenticationToken = new SmsAuthenticationToken(user,user.getAuthorities());
        authenticationToken.setDetails(smsAuthenticationToken.getDetails());
        //MyBeanUtils.copyProperties(authenticationToken,smsAuthenticationToken);
        return authenticationToken;
    }

    /**
     * AuthenticationManager 判断 SmsAuthenticationProvider 处理哪一类token（SmsAuthenticationToken）
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
