package com.jin.yin.scurity.modelus.security.validate.sms.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

/**
 * @author: liangjinyin
 * @Date: 2018-09-03
 * @Description: 短信验证码的Token
 */
public class SmsAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 420L;
    private final Object principal;

    public SmsAuthenticationToken(Object principal) {
        super( null);
        this.principal = principal;
        this.setAuthenticated(false);
    }

    public SmsAuthenticationToken(Object principal,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}