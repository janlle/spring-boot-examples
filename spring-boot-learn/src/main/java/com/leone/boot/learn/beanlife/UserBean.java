package com.leone.boot.learn.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

/**
 * <p> BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean
 * <p>
 * Spring 容器初始化 bean 和 bean 销毁前所做的操作定义方式有三种：
 * 1.通过bean实现 InitializingBean 和 DisposableBean 接口
 * 2.通过 @PostConstruct 和 @PreDestroy 注解实现初始化后和 bean销毁之前的操作
 * 3.在 xml 中指定 init-method 和  destroy-method 或 JavaConfig 指定initMethod和destroyMethod
 *
 * @author leone
 * @since 2019-06-20
 **/
public class UserBean implements ApplicationContextAware,
  ApplicationEventPublisherAware, BeanClassLoaderAware, BeanFactoryAware,
  BeanNameAware, EnvironmentAware, ResourceLoaderAware, InitializingBean, DisposableBean {

    private String name;

    /**
     * 自定义方法
     */
    public void sayHello(String message) {
        System.out.println("hello " + message);
    }

    /**
     * instantiate bean 对象实例化
     */
    public UserBean() {
        System.err.println("01 - 构造方法....");
    }

    /**
     * 设置对象属性
     */
    public void setName(String name) {
        System.err.println("02 - 设置属性setter方法...");
        this.name = name;
    }

    /**
     * 调用 BeanNameAware 的 setBeanName方法
     */
    @Override
    public void setBeanName(String s) {
        System.err.println("03 - setBeanName: " + s);
    }

    /**
     * 设置 bean 的 ClassLoader
     */
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.err.println("04 - setBeanClassLoader: " + classLoader.getClass().getName());
    }

    /**
     * 设置 BeanFactory
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.err.println("05 - setBeanFactory: " + beanFactory.getClass());
    }

    /**
     * 设置上下文环境
     */
    @Override
    public void setEnvironment(Environment environment) {
        System.err.println("06 - setEnvironment: " + environment.getClass());
    }

    /**
     * 这只资源加载器
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.err.println("07 - setResourceLoader: " + resourceLoader.getClass());
    }

    /**
     * 设置上下文的事件发送器
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.err.println("08 - setApplicationEventPublisher: " + applicationEventPublisher.getClass());
    }

    /**
     * 设置应用上下文
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.err.println("09 - setApplicationContext: " + applicationContext.getClass());
    }


    // ---------------------------------- 初始化方法 -------------------------------

    /**
     * 初始化方式一 设置属性后执行
     * 实现接口，InitializingBean 重写afterPropertiesSet 方法
     */
    public void afterPropertiesSet() throws Exception {
        System.err.println("10 - 自定义初始化方法1...");
    }

    /**
     * 初始化方式二
     * 注解方式
     */
    //@PostConstruct
    public void postConstruct() {
        System.err.println("10 - 自定义初始化方法2...");
    }


    /**
     * 初始化方式三 手动指定方法名称
     */
    public void initMethod() {
        System.err.println("10 - 自定义初始化方法3...");
    }


    // ---------------------------------- 销毁方法 -------------------------------

    /**
     * 销毁方式一
     * 实现接口，重写 DisposableBean 的 destroy 的方法
     */
    //@Override
    public void destroy() throws Exception {
        System.err.println("11 - 自定义销毁方法1...");
    }


    /**
     * 销毁方式二
     * 注解方式
     */
    //@PreDestroy
    public void preDestroy() {
        System.err.println("11 - 自定义销毁方法2");
    }


    /**
     * 销毁方式三
     * 手动指定方法名称
     */
    public void destroyMethod() {
        System.err.println("11 - 自定义销毁方法3...");
    }


}
