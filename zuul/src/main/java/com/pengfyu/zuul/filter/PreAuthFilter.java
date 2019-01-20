package com.pengfyu.zuul.filter;

import com.pengfyu.zuul.util.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Service;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Service
public class PreAuthFilter extends AbstractPreAuthenticatedProcessingFilter implements Filter {

    @Autowired
    public void setMyAccessDecisionManager(AuthenticationManager accessDecisionManager) {
        super.setAuthenticationManager(accessDecisionManager);
    }

    /**
     * 重写，返回用户名，这个用户名是经过其他方式认证过
     */
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        // 可以通过request获取当前认证过的用户名，比如通过参数、HTTP请求头或者Cookie获取token，再通过token调用第三方接口获取用户名
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String authToken = null;
        if (StringUtils.isNotBlank(authHeader) && authHeader.length() >8) {
            authToken = authHeader.substring(7);
        }
        //获取角色ID，多个以逗号分割
        String userRoles = JwtUtil.getRolesFromToken(authToken);
        String username = JwtUtil.getUserUsernameFromToken(authToken);
        if (username == null) {
            throw new UsernameNotFoundException("Token is invalid!");
        }
        Map map = new HashMap();
        map.put("roles",userRoles);
        map.put("username",username);
        return map;
    }

    /**
     * 这个方法一般情况下不需要重写，直接返回空字符串即可
     */
    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "";
    }
}