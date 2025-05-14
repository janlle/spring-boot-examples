package com.leone.boot.learn.config;

import com.leone.boot.learn.beanlife.AppBeanPostProcessor;
import com.leone.boot.learn.beanlife.CatBean;
import com.leone.boot.learn.beanlife.DogBean;
import com.leone.boot.learn.beanlife.UserBean;
import com.leone.boot.learn.event.EmailListener;
import com.leone.boot.learn.factory.BullFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * <p> 声明为 spring 容器的启动类
 * ConfigurableBeanFactory.SCOPE_SINGLETON
 * ConfigurableBeanFactory.SCOPE_PROTOTYPE
 *
 * @author leone
 * @since 2019-06-21
 **/
public class SpringJavaConfig {

    @Lazy
    @Scope("singleton") // singleton prototype
    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public UserBean userBean() {
        UserBean user = new UserBean();
        user.setName("leone");
        return user;
    }


    //-------------- 自定义bean --------------
    //@Bean
    public EmailListener emailListener() {
        return new EmailListener();
    }

    //@Bean
    public BullFactoryBean bullFactoryBean() {
        return new BullFactoryBean();
    }

    @Bean
    public DogBean dogBean() {
        return new DogBean();
    }

    @Bean
    public CatBean catBean() {
        return new CatBean();
    }

    @Bean
    public AppBeanPostProcessor appBeanPostProcessor() {
        return new AppBeanPostProcessor();
    }


}
