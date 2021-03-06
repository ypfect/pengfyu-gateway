/**
 * 
 */
package com.pengfyu.zuul.service;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;


/**
 * @author zhailiang
 *
 */
@Component("rbacService")
public class RbacServiceImpl implements RbacService {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		//直接获取的可能是anymourse
		Object principal = authentication.getPrincipal();

		boolean hasPermission = false;

		if (principal instanceof User) {
			//如果用户名是admin，就永远返回true
			if (StringUtils.equals(((User) principal).getUsername(), "admin")) {
				hasPermission = true;
			} else {
				// 读取用户所拥有权限的所有URL
				//((User) principal).getUrls();
				Set<String> urls = new HashSet<>();//此处获取用户所拥有的资源列表
				for (String url : urls) {
					if (antPathMatcher.match(url, request.getRequestURI())) {
						hasPermission = true;
						break;
					}
				}
			}
		}

		return hasPermission;
	}

}
