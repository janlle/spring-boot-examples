package com.leone.boot.learn.config;

import com.leone.boot.learn.beanlife.UserBean;
import com.leone.boot.learn.event.EmailListener;
import com.leone.boot.learn.factory.BullFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * <p> 声明为 spring 容器的启动类
 *
 * @author leone
 * @since 2019-06-21
 **/
public class SpringJavaConfig {

    @Lazy
    @Scope("prototype")
    @Bean(/* initMethod = "initMethod", destroyMethod = "destroyMethod" */)
    public UserBean userBean() {
        return new UserBean();
    }


    //-------------- event --------------
    @Bean
    public EmailListener emailListener() {
        return new EmailListener();
    }

    @Bean
    public BullFactoryBean bullFactoryBean() {
        return new BullFactoryBean();
    }

}
