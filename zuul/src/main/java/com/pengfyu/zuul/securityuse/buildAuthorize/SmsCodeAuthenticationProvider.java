package com.pengfyu.zuul.securityuse.buildAuthorize;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

/**
 * @Author stanley.yu
 * @Description
 * @Date 2019/1/22 22:37
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * smsCode authentication method
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken smsToken = (SmsCodeAuthenticationToken)authentication;
        String mobile = smsToken.getPrincipal();
        UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);
        if (userDetails == null){
            throw new InternalAuthenticationServiceException("can't find user message...");
        }
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        SmsCodeAuthenticationToken smsCodeAuthenticationToken = new SmsCodeAuthenticationToken(mobile,authorities);
        smsCodeAuthenticationToken.setDetails(smsCodeAuthenticationToken.getCredentials());
        return smsCodeAuthenticationToken;
    }

    /**
     * 适配器的配置 一个token  一个handler
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return (SmsCodeAuthenticationToken.class.isAssignableFrom(authentication));
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
