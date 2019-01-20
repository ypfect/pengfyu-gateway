package com.pengfyu.zuul.springsecurity.validate;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author stanley.yu
 * @Description
 * @Date 2019/1/20 22:02
 */
public class ValidateCode implements Serializable {
    //验证码
    private String code;
    //过期时间
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public boolean isExpired(){return LocalDateTime.now().isAfter(expireTime);}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
