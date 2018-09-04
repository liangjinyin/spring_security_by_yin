package com.jin.yin.scurity.common.configuration;

import com.jin.yin.scurity.common.interceptor.LoginHandler;
import com.jin.yin.scurity.modelus.security.validate.sms.config.SmsCodeFilter;
import com.jin.yin.scurity.modelus.security.validate.sms.config.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author: liangjinyin
 * @Date: 2018-08-22
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private LoginHandler loginHandler;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SmsCodeFilter smsCodeFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .apply(springSocialConfigurer)
                .and()
                .apply(validateCodeSecurityConfig)
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/authentication/form")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(loginHandler)
                .failureHandler(loginHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/login.html","/druid/*", "/customer/**", "/sms/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
        ;
    }
}
