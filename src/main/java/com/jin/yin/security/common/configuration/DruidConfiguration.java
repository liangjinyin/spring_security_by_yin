package com.jin.yin.security.common.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.jin.yin.security.common.constants.properties.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author: liangjinyin
 * @Date: 2018-08-22
 * @Description: druid配置
 */
@Configuration
public class DruidConfiguration {

    @Autowired
    private SystemProperties systemProperties;
    @Bean
    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        StatViewServlet statViewServlet = new StatViewServlet();
        servletRegistrationBean.setServlet(statViewServlet);
        servletRegistrationBean.addUrlMappings("/druid/*");
        servletRegistrationBean.addInitParameter("allow","127.0.0.1,132.97.8.78");
        servletRegistrationBean.addInitParameter("loginUsername",systemProperties.getDruid().getLoginname());
        servletRegistrationBean.addInitParameter("loginPassword",systemProperties.getDruid().getLoginpassword());
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        WebStatFilter webStatFilter = new WebStatFilter();
        filterRegistrationBean.setFilter(webStatFilter);

        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions","*.css,*.html,*.js,/druid/*");
        return filterRegistrationBean;
    }

    @Bean(name = "druidDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }
}
