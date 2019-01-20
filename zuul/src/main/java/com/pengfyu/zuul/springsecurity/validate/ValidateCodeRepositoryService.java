package com.pengfyu.zuul.springsecurity.validate;

import com.pengfyu.zuul.common.GatewayRet;

/**
 * @Author stanley.yu
 * @Description  验证码服务包含： save delete query
 *
 * @Date 2019/1/20 22:14
 */
public interface ValidateCodeRepositoryService {
    /**
     * 保存验证码
     * @param mobile
     * @param code
     */
    GatewayRet save(String mobile,String code);
    /**
     * 获取验证码
     * @param mobile
     * @return
     */
    GatewayRet get(String mobile);
    /**
     * 移除验证码
     * @param mobile
     */
    GatewayRet remove(String mobile);
}
