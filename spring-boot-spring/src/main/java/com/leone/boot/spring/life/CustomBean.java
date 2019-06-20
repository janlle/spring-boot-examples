package com.leone.boot.spring.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.*;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-20
 **/
public class CustomBean implements ApplicationContextAware,
        ApplicationEventPublisherAware, BeanClassLoaderAware, BeanFactoryAware,
        BeanNameAware, EnvironmentAware, ImportAware, ResourceLoaderAware {


    public void init() {
        System.err.println("init...");
    }

    public void destroy() {
        System.err.println("destroy...");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.err.println("setBeanClassLoader ClassLoaderName: " + classLoader.getClass().getName());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.err.println("setBeanFactory bean is singleton: " + beanFactory.isSingleton("customBean"));
    }

    @Override
    public void setBeanName(String s) {
        System.err.println("setBeanName bean name: " + s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.err.println("setApplicationContext bean definition names: " + Arrays.toString(applicationContext.getBeanDefinitionNames()));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.err.println("setApplicationEventPublisher");
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.err.println("setEnvironment");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.err.println("setResourceLoader");
    }

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        System.err.println("setImportMetadata");
    }

}
