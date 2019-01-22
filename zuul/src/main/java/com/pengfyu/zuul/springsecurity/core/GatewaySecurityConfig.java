package com.pengfyu.zuul.springsecurity.core;

import com.pengfyu.zuul.springsecurity.authorize.AuthorizeConfigManager;
import com.pengfyu.zuul.springsecurity.properties.SecurityProperties;
import com.pengfyu.zuul.springsecurity.validate.filter.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @Author stanley.yu
 * @Description
 * @Date 2019/1/22 17:20
 */
@Component
public class GatewaySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;


    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

//    @Autowired
//    private FormAuthenticationConfig formAuthenticationConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        formAuthenticationConfig.configure(http);
        http.formLogin();
        http.apply(validateCodeSecurityConfig)
                .and()
                //记住我配置，如果想在'记住我'登录时记录日志，可以注册一个InteractiveAuthenticationSuccessEvent事件的监听器
                .csrf().disable();
        authorizeConfigManager.config(http.authorizeRequests());

    }
}
