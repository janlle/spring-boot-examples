package com.andy.mvc.config;

import com.andy.mvc.annocation.CustomerAnno;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Leone
 * @since 2018-06-29 22:30
 **/
@Slf4j
@Component
public class SpringTool implements ApplicationContextAware {

    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("setApplicationContext...");
        context = applicationContext;

        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(CustomerAnno.class);
        for (Object object : beans.values()) {
            try {
                String value = object.getClass().getAnnotation(CustomerAnno.class).value();
                log.info("values:{}", value);
                Method method = object.getClass().getMethod("init", new Class[]{String.class});
                Object invoke = method.invoke(object, "kobe");
                log.info("result:{}", invoke);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

}
