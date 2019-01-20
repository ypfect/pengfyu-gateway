package com.pengfyu.zuul.springsecurity.validate;

import com.pengfyu.zuul.common.GatewayRet;

/**
 * @Author stanley.yu
 * @Description
 * @Date 2019/1/20 22:10
 */
public interface ValidateCodeSender {
    GatewayRet send(String mobile,String code);
}
