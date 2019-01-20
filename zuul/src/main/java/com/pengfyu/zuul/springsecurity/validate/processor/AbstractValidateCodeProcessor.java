package com.pengfyu.zuul.springsecurity.validate.processor;

import com.pengfyu.zuul.springsecurity.validate.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @Author stanley.yu
 * @Description
 * 模板方法，定义流程
 * @Date 2019/1/20 22:42
 * todo  ------------------------------------------
 */
public abstract class AbstractValidateCodeProcessor <C extends ValidateCode> implements ValidateCodeProcessor {

    /**
     * 依赖搜索 {@link ValidateCodeGenerator}查找所有ValidateCodeGenerator接口的实现
     */
    @Autowired
    private Map<String,ValidateCodeGenerator> validateCodeGeneratorMap;

    @Autowired
    private ValidateCodeRepositoryService validateCodeRepository;


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
        //生成验证码之后进行redisson保存
//        validateCodeRepository.save()//TODO 同样的方法进行依赖搜索，获取对应的repository进行操作

    }

    @Override
    public void validate(ServletWebRequest servletWebRequest) {

    }


    public C generate(ServletWebRequest request){
        ValidateCodeType type = getProcessorType(request);
        String onValidate = type.getParamNameOnValidate();
        //获取实现中的对应类型的生成器
        ValidateCodeGenerator codeGenerator = validateCodeGeneratorMap.get(onValidate + "CodeGenerator");
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


    // ~ 抽象方法
    // ===================================================



}
