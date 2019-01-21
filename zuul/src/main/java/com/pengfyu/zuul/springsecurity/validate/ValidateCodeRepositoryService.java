package com.pengfyu.zuul.springsecurity.validate;

import com.pengfyu.zuul.common.GatewayRet;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author stanley.yu
 * @Description  验证码服务包含： save delete query
 *
 * @Date 2019/1/20 22:14
 */
public interface ValidateCodeRepositoryService {
    /**
     * 保存验证码
     * @param request
     * @param code
     */
    GatewayRet save(ServletWebRequest request, ValidateCode code);
    /**
     * 获取验证码
     * @param request
     * @return
     */
    GatewayRet get(ServletWebRequest request, ValidateCode code);
    /**
     * 移除验证码
     * @param request
     */
    GatewayRet remove(ServletWebRequest request, ValidateCode code);
}
