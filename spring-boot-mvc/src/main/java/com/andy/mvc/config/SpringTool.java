package com.andy.mvc.config;

import com.andy.mvc.annocation.CustomerAnno;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-29 22:30
 **/
@Component
public class SpringTool implements ApplicationContextAware {

    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;

        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(CustomerAnno.class);



    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }




}
