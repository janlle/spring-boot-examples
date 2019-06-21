package com.leone.boot.spring;

import com.leone.boot.spring.config.AppConfig;
import com.leone.boot.spring.life.CustomBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author leone
 * @since 2018-06-29
 **/
@SpringBootApplication
public class App {

    public static void main(String[] args) throws InterruptedException {

        //FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("D:\\spring.xml");
        //ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        Thread.sleep(5000);

        applicationContext.getBean(CustomBean.class).helloWorld();

        Thread.sleep(2000);

        applicationContext.getBean(CustomBean.class).helloWorld();

        applicationContext.close();

        //SpringApplication.run(App.class, args);

    }
}
