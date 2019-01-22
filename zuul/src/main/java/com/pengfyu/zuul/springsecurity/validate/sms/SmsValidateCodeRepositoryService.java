package com.pengfyu.zuul.springsecurity.validate.sms;

import com.pengfyu.zuul.common.GatewayRet;
import com.pengfyu.zuul.common.ValidateCodeException;
import com.pengfyu.zuul.redisson.RedissonUtils;
import com.pengfyu.zuul.springsecurity.validate.ValidateCode;
import com.pengfyu.zuul.springsecurity.validate.ValidateCodeRepositoryService;
import org.apache.commons.lang.StringUtils;
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
public class SmsValidateCodeRepositoryService implements ValidateCodeRepositoryService {

    @Override
    public GatewayRet save(ServletWebRequest request, ValidateCode key) {
        //生产key
        UUID uuid = UUID.randomUUID();
        request.getResponse().addCookie(new Cookie("smsCodeCookie",uuid.toString()));
        //存到redis
        String keyCode = key.getCode();
        RBucket<String> rBucket = RedissonUtils.getRBucket(uuid.toString());
        rBucket.set(keyCode,200,TimeUnit.SECONDS);
        return GatewayRet.successExploreRet();
    }

    @Override
    public ValidateCode get(ServletWebRequest request) {
        Cookie[] cookies = request.getRequest().getCookies();
        String key = "";
        for (Cookie cookie : cookies) {
            if ("smsCodeCookie".equals(cookie.getName())){
                key=cookie.getValue();
            }
        }
        if (StringUtils.isEmpty(key)){
            throw new ValidateCodeException("can't find the key by cookie...");
        }
        RBucket<String> rBucket = RedissonUtils.getRBucket(key);
        //long toLive = rBucket.remainTimeToLive();//判断时间没有必要，如果过期了本来也没了。。只需要判断是否还存在toLive<=0
        String validateKeyInRedis = rBucket.get();
        if ( validateKeyInRedis== null){
            return null;
        }
        ValidateCode validateCode = new ValidateCode(validateKeyInRedis, -1);
        return validateCode;
    }

    @Override
    public GatewayRet remove(ServletWebRequest request) {
        Cookie[] cookies = request.getRequest().getCookies();
        String key = "";
        for (Cookie cookie : cookies) {
            if ("smsCodeCookie".equals(cookie.getName())){
                key=cookie.getValue();
            }
        }
        RedissonUtils.getRBucket(key).delete();
        return GatewayRet.successExploreRet();
    }
}
