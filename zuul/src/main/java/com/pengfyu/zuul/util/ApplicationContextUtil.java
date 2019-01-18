package com.pengfyu.zuul.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * spring bean 工具类
 */
@Service
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext context = null;

    public ApplicationContextUtil() {
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (ApplicationContextUtil.context == null)
        {
            this.context = context;
        }

    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(String id, Class<T> cls) {
        return context.getBean(id, cls);
    }

    public static <T> T getBean(String id) {
        if (context.containsBean(id)) {
            return (T)context.getBean(id);
        } else {
            throw new RuntimeException(id + " bean not exists.");
        }
    }

    public static <T> T getBean(Class<?> type) {
        return (T)context.getBean(type);
    }

    public static boolean isBeanExist(String id) {
        return context.containsBean(id);
    }

    public static boolean containsBean(Class<?> type) {
        return !context.getBeansOfType(type).isEmpty();
    }

    public static <T> Map<String, T> getBeans(Class<T> type) {
        return context.getBeansOfType(type);
    }

}
