package com.pengfyu.zuul.springsecurity.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author stanley.yu
 * @Description 验证码处理器 定义验证方法和创建
 * @Date 2019/1/20 22:39
 */
public interface ValidateCodeProcessor {

    /**
     * 创建校验码
     *
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest
     * @throws Exception
     */
    void validate(ServletWebRequest servletWebRequest);
}
