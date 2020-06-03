package com.leone.boot.learn;

import com.leone.boot.learn.beanlife.UserBean;
import com.leone.boot.learn.config.SpringBootstrapConfig;
import com.leone.boot.learn.event.EmailEvent;
import com.leone.boot.learn.event.EmailSource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author leone
 * @since 2018-06-29
 **/
@SpringBootApplication
public class SpringApp {

    public static void main(String[] args) throws InterruptedException {

        // 通过文件系统加载配置文件初始化一个 Spring 容器
        //FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("D:\\spring.xml");

        // 通过 classpath 下的配置文件初始化一个 spring 容器
        //ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        // 通过一个配置类初始化一个 spring 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringBootstrapConfig.class);

        Thread.sleep(5000);

        applicationContext.getBean(UserBean.class).sayHello("world");

        Thread.sleep(2000);

        applicationContext.getBean(UserBean.class).sayHello("leone");

        applicationContext.publishEvent(new EmailEvent(new EmailSource("hello@gmail.com", "hello world")));

        // 调用容器的关闭方法关闭 Spring 上下文
        applicationContext.close();

        //SpringApplication.run(App.class, args);

    }
}
