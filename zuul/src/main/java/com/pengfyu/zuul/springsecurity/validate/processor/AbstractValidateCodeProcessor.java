package com.pengfyu.zuul.springsecurity.validate.processor;

import com.pengfyu.zuul.springsecurity.validate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author stanley.yu
 * @Description
 * 模板方法，定义流程
 * @Date 2019/1/20 22:42
 * todo  ------------------------------------------
 */
public abstract class AbstractValidateCodeProcessor <C extends ValidateCode> implements ValidateCodeProcessor {

    @Autowired
    private ValidateCodeRepositoryService validateCodeRepository;

    @Autowired
    private ValidateCodeGenerator codeGenerator;

    /**
     * 根据请求执行验证码流程。。
     * 生成验证码
     * 验证码的持久化增删改...
     * @param request
     * @throws Exception
     */
    @Override
    public void create(ServletWebRequest request) throws Exception {
        ValidateCode generate = codeGenerator.generate(request);
//        validateCodeRepository.save(generate)
    }

    @Override
    public void validate(ServletWebRequest servletWebRequest) {

    }


    // ~ 抽象方法
    // ===================================================



}
