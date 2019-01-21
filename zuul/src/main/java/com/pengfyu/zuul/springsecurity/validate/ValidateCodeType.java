package com.pengfyu.zuul.springsecurity.validate;


import com.pengfyu.zuul.springsecurity.properties.SecurityConstants;

/**
 * @Author stanley.yu
 * @Description  enum 可以定义抽象方法，每个枚举对象返回对应实现
 * @Date 2019/1/20 22:10
 */
public enum ValidateCodeType {
	
	/**
	 * 短信验证码
	 */
	SMS {
		@Override
		public String getParamNameOnValidate() {
			return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
		}
	},
	/**
	 * 图片验证码
	 */
	IMAGE {
		@Override
		public String getParamNameOnValidate() {
			return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
		}

	};

	/**
	 * 校验时从请求中获取的参数的名字
	 * @return
	 */
	public abstract String getParamNameOnValidate();
//	public abstract String fuck();

}