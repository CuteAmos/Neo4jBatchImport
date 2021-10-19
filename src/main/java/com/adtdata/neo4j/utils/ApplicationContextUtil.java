package com.adtdata.neo4j.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author aixiaobai
 * @date 2021/9/30 11:29
 */
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext is null.");
        }else {
            return applicationContext;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static<T> T getBean(Class<T> cla) {
        return applicationContext.getBean(cla);
    }

    public static<T> T getBean(String beanName,Class<T> cla) {
        return applicationContext.getBean(beanName,cla);
    }
}
