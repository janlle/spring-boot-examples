package com.leone.boot.learn.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * <p>
 *
 * @author leone
 * @since 2024-12-23
 **/
public class AppBeanPostProcessor implements BeanPostProcessor {

    public AppBeanPostProcessor() {
        System.out.println("AppBeanPostProcessor 实例化...");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.err.printf("05 - 前置处理器 postProcessBeforeInitialization %s%n", beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.err.printf("07 - 后置处理器 postProcessAfterInitialization %s%n", beanName);
        return bean;
    }


}
