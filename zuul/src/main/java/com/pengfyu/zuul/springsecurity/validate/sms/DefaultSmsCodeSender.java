package com.pengfyu.zuul.springsecurity.validate.sms;

import com.pengfyu.zuul.common.GatewayRet;
import com.pengfyu.zuul.springsecurity.validate.ValidateCodeSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author stanley.yu
 * @Description
 * @Date 2019/1/20 22:10
 */
@Service
public class DefaultSmsCodeSender implements ValidateCodeSender {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.imooc.security.core.validate.code.sms.SmsCodeSender#send(java.lang.String, java.lang.String)
	 */
	@Override
	public GatewayRet send(String mobile, String code) {
		logger.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
		logger.info("向手机"+mobile+"发送短信验证码"+code);
		return GatewayRet.successExploreRet(110,"发送短信成功！");
	}

}
