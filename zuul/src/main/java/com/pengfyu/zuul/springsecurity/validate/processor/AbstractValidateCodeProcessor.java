package com.pengfyu.zuul.springsecurity.validate.processor;

import com.pengfyu.zuul.common.ValidateCodeException;
import com.pengfyu.zuul.redisson.RedissonUtils;
import com.pengfyu.zuul.springsecurity.validate.*;
import com.pengfyu.zuul.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    // 依赖搜索
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
        redissonSave(request,generate);
        send(request, generate);

    }

    protected abstract void send(ServletWebRequest request, C generate);

    /**
     * save validateCode in redisson strategy
     * search from applicationContext where adapter validateType
     * @param generate
     * @param request
     */
    private void redissonSave(ServletWebRequest request,C generate) {
        ValidateCodeType type = getProcessorType(request);
        String onValidate = type.getParamNameOnValidate();
        ValidateCodeRepositoryService repositoryService = repositoryServiceMap.get(onValidate + "ValidateCodeRepositoryService");
        repositoryService.save(request,generate);
    }

    private void generateRedissonKey(C generate) {
        String code = generate.getCode();
        int expireSecond = TimeUtil.calculationTimeDiff(LocalDateTime.now(), generate.getExpireTime());
        RBucket<Object> bucket = RedissonUtils.getRBucket(code);
//        bucket.setAsync()

    }


    @Override
    public void validate(ServletWebRequest servletWebRequest) {

    }


    public C generate(ServletWebRequest request){
        ValidateCodeType type = getProcessorType(request);
        String onValidate = type.getParamNameOnValidate();
        //获取实现中的对应类型的生成器
        ValidateCodeGenerator codeGenerator = validateCodeGeneratorMap.get(onValidate + "CodeGenerator");
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


    public String getMobileNumber(ServletWebRequest request){
        String mobile = (String) request.getRequest().getAttribute("mobile");
        return mobile;
    }


    // ~ 抽象方法
    // ======================================================================================



}
