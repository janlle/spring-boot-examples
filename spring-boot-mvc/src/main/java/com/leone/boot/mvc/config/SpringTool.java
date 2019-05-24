package com.leone.boot.mvc.config;

import com.leone.boot.mvc.anno.CustomerAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author leone
 * @since 2018-06-29
 **/
@Slf4j
@Component
public class SpringTool implements ApplicationContextAware {

    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("setApplicationContext...");
        context = applicationContext;
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(CustomerAnnotation.class);
        for (Object object : beans.values()) {
            try {
                String value = object.getClass().getAnnotation(CustomerAnnotation.class).value();
                log.info("values: {}", value);
                Method method = object.getClass().getMethod("init");
                Object invoke = method.invoke(object);
                log.info("result: {}", invoke);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

}
