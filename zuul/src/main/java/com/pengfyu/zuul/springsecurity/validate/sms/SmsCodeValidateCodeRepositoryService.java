package com.pengfyu.zuul.springsecurity.validate.sms;

import com.pengfyu.zuul.common.GatewayRet;
import com.pengfyu.zuul.redisson.RedissonUtils;
import com.pengfyu.zuul.springsecurity.validate.ValidateCode;
import com.pengfyu.zuul.springsecurity.validate.ValidateCodeRepositoryService;
import org.redisson.api.RBucket;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.Cookie;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author stanley.yu
 * @Description
 * @Date 2019/1/21 22:42
 */
@Service
public class SmsCodeValidateCodeRepositoryService implements ValidateCodeRepositoryService {

    @Override
    public GatewayRet save(ServletWebRequest request, ValidateCode key) {
        //生产key
        UUID uuid = UUID.randomUUID();
        request.getResponse().addCookie(new Cookie("smsCodeCookie",uuid.toString()));
        //存到redis
        String keyCode = key.getCode();
        RBucket<String> rBucket = RedissonUtils.getRBucket(uuid.toString());
        rBucket.set(keyCode,60,TimeUnit.SECONDS);
        return GatewayRet.successExploreRet();
    }

    @Override
    public ValidateCode get(ServletWebRequest request) {
        String uuidKey = (String)request.getRequest().getAttribute(" smsCodeCookie");
        RBucket<String> rBucket = RedissonUtils.getRBucket(uuidKey);
        long toLive = rBucket.remainTimeToLive();
        String validateKeyInRedis = rBucket.get();
        ValidateCode validateCode = new ValidateCode(validateKeyInRedis, (int) toLive);
        return validateCode;
    }

    @Override
    public GatewayRet remove(ServletWebRequest request) {
        String uuidKey = (String)request.getRequest().getAttribute(" smsCodeCookie");
        RedissonUtils.getRBucket(uuidKey).delete();
        return GatewayRet.successExploreRet();
    }
}
