package com.pengfyu.zuul.springsecurity.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author stanley.yu
 * @Description
 * @Date 2019/1/20 23:18
 */
public interface ValidateCodeGenerator {
    /**
     * 生成校验码
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);
}
