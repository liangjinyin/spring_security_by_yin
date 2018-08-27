package com.jin.yin.scurity.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: liangjinyin
 * @Date: 2018-08-22
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                //.antMatchers("/druid/*","/user/*")
                .antMatchers("/druid/*")
                .permitAll()
            .and()
            .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/authentication/form")
                .usernameParameter("username")
                .passwordParameter("password")
//                .successHandler()
//                .failureHandler()
            .and()
            .authorizeRequests()
            .antMatchers("/login.html").permitAll()
            .anyRequest()
            .authenticated()

        ;
    }
}
