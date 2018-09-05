package com.jin.yin.security.common.configuration;

import com.jin.yin.security.common.interceptor.LoginHandler;
import com.jin.yin.security.models.security.validate.sms.config.SmsCodeFilter;
import com.jin.yin.security.models.security.validate.sms.config.ValidateCodeSecurityConfig;
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
                .sessionManagement()
                /** 当session失效的时候跳转的url*/
                .invalidSessionUrl("")
                /** 设置session的最大数量为1*/
                .maximumSessions(1)
                    /** 当session的数量达到设置的数量时，会阻止*/
                    .maxSessionsPreventsLogin(true)
                    .and()
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