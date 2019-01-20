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
     * @param validateCodeType
     */
    GatewayRet save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);
    /**
     * 获取验证码
     * @param request
     * @param validateCodeType
     * @return
     */
    GatewayRet get(ServletWebRequest request, ValidateCodeType validateCodeType);
    /**
     * 移除验证码
     * @param request
     * @param codeType
     */
    GatewayRet remove(ServletWebRequest request, ValidateCodeType codeType);
}
