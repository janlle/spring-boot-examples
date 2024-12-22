package com.leone.boot.learn;

import com.leone.boot.learn.beanlife.UserBean;
import com.leone.boot.learn.config.SpringJavaConfig;
import com.leone.boot.learn.event.EmailEvent;
import com.leone.boot.learn.event.EmailSource;
import com.leone.boot.learn.factory.BullFactoryBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
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
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringJavaConfig.class);

        // --------------------------- test ----------------------------------
        testBeanLife(applicationContext);
        // --------------------------- test ----------------------------------

        // 调用容器的关闭方法关闭 Spring 上下文
        applicationContext.close();
        //SpringApplication.run(App.class, args);
    }

    public static void testBeanLife(ApplicationContext context) throws InterruptedException {
        context.getBean(UserBean.class).sayHello("world");
        Thread.sleep(500);
        context.getBean(UserBean.class).sayHello("leone");
    }

    public static void testEvent(ApplicationContext context) {
        context.publishEvent(new EmailEvent(new EmailSource("hello@gmail.com", "hello world")));
    }

    public static void testFactoryBean(ApplicationContext context) {
        BullFactoryBean bean1 = (BullFactoryBean) context.getBean("bullFactoryBean");
        BullFactoryBean bean2 = (BullFactoryBean) context.getBean("&bullFactoryBean");
        System.out.println(bean1.equals(bean2));
        System.out.println(bean1.getData());
        System.out.println(bean2.getData());
    }


}
