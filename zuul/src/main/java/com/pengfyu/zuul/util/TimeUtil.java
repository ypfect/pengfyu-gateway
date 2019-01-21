package com.pengfyu.zuul.util;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @Author stanley.yu
 * @Description
 * @Date 2019/1/21 13:55
 */
public class TimeUtil {

    public static int calculationTimeDiff(LocalDateTime now,LocalDateTime feature ){
        int diffSeconds = 0;
        Duration duration = Duration.between(now,feature);
        long seconds = duration.getSeconds();
        if (seconds<0){
            diffSeconds = 0;
        }
        diffSeconds=(int) seconds;
        return diffSeconds;
    }
}
