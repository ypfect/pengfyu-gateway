package com.pengfyu.zuul.springsecurity.validate.processor;

import com.pengfyu.zuul.common.GatewayRet;
import com.pengfyu.zuul.common.ValidateCodeException;
import com.pengfyu.zuul.springsecurity.validate.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @Author stanley.yu
 * @Description
 * 模板方法，定义流程
 * @Date 2019/1/20 22:42
 */
public abstract class AbstractValidateCodeProcessor <C extends ValidateCode> implements ValidateCodeProcessor {

    /**
     * 依赖搜索 {@link ValidateCodeGenerator}查找所有ValidateCodeGenerator接口的实现
     */
    @Autowired
    private Map<String,ValidateCodeGenerator> validateCodeGeneratorMap;


    @Autowired
    private Map<String,ValidateCodeRepositoryService> repositoryServiceMap;



    /**
     * 根据请求执行验证码流程。。
     * 生成验证码
     * 验证码的持久化增删改...
     * @param request
     * @throws Exception
     */
    @Override
    public void create(ServletWebRequest request) throws Exception {
        C generate = generate(request);
        redissonSave(request, generate);
        sendCode(request,generate);
    }

    /**
     * 验证方法。
     * @param servletWebRequest
     */
    @Override
    public void validate(ServletWebRequest servletWebRequest) {
        ValidateCodeType processorType = getProcessorType(servletWebRequest);
        String nameOnValidate = processorType.getParamNameOnValidate();
        ValidateCodeRepositoryService repositoryService = repositoryServiceMap.get(nameOnValidate + "ValidateCodeRepositoryService");
        C validateCodeInRedisson =(C) repositoryService.get(servletWebRequest);
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),
                    processorType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(processorType + "验证码的值不能为空");
        }

        if (validateCodeInRedisson == null) {
            throw new ValidateCodeException(processorType + "验证码不存在");
        }

        if (validateCodeInRedisson.isExpired()) {
            repositoryService.remove(servletWebRequest);
            throw new ValidateCodeException(processorType + "验证码已过期");
        }

        if (!StringUtils.equals(validateCodeInRedisson.getCode(), codeInRequest)) {
            throw new ValidateCodeException(processorType + "验证码不匹配");
        }

        repositoryService.remove(servletWebRequest);

    }


    protected abstract GatewayRet sendCode(ServletWebRequest request, C generate) throws ServletRequestBindingException;

    /**
     * 根据请求获取对应的处理类，做redisson保存操作
     * @param request
     * @param generate
     * @return
     */
    private  GatewayRet redissonSave(ServletWebRequest request, C generate){
        ValidateCodeType processorType = getProcessorType(request);
        String nameOnValidate = processorType.getParamNameOnValidate();
        ValidateCodeRepositoryService repositoryService = repositoryServiceMap.get(nameOnValidate + "ValidateCodeRepositoryService");
        GatewayRet ret = repositoryService.save(request, generate);
        return ret;
    }


    public C generate(ServletWebRequest request){
        ValidateCodeType type = getProcessorType(request);
        String onValidate = type.getParamNameOnValidate();
        //获取实现中的对应类型的生成器
        ValidateCodeGenerator codeGenerator = validateCodeGeneratorMap.get(onValidate + "Generator");
        if (codeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + onValidate + "CodeGenerator" + "不存在");
        }

        return (C)codeGenerator.generate(request);
    }

    /**
     * 根据请求路径获取请求的验证码类型
     * @param request
     * @return
     */
    public ValidateCodeType getProcessorType(ServletWebRequest request){
        String requestURI = request.getRequest().getRequestURI();
        String type = StringUtils.substringAfter(requestURI, "/code/");
        return ValidateCodeType.valueOf(StringUtils.upperCase(type));
    }

}
