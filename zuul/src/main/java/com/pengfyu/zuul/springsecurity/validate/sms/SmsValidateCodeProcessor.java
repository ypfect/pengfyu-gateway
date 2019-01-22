/**
 * 
 */
package com.pengfyu.zuul.springsecurity.validate.sms;

import com.pengfyu.zuul.common.GatewayRet;
import com.pengfyu.zuul.springsecurity.properties.SecurityConstants;
import com.pengfyu.zuul.springsecurity.validate.ValidateCode;
import com.pengfyu.zuul.springsecurity.validate.ValidateCodeSender;
import com.pengfyu.zuul.springsecurity.validate.processor.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author stanley.yu
 * @Description
 * @Date 2019/1/20 22:10
 */
@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private ValidateCodeSender codeSender;
	
	@Override
	protected GatewayRet sendCode(ServletWebRequest request, ValidateCode validateCode) throws ServletRequestBindingException {
		String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		return codeSender.send(mobile, validateCode.getCode());
	}

}
