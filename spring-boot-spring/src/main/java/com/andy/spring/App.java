package com.andy.spring;

import com.andy.spring.beans.Person;
import com.andy.spring.beans.Student;
import com.andy.spring.scan.Teacher;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author: Leone
 * @since: 2018-06-29 23:18
 **/
public class App {

    public static void main(String[] args) {

//        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("D:\\spring.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
//        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring.xml"));

//        System.out.println("getApplicationName:" + applicationContext.getApplicationName());
//        System.out.println("getDisplayName:" + applicationContext.getDisplayName());
//        System.out.println("getId:" + applicationContext.getId());
//        System.out.println("getStartupDate:" + applicationContext.getStartupDate());
//
//        Person person1 = applicationContext.getBean("001", Person.class);
//        Teacher teacher = applicationContext.getBean("teach", Teacher.class);
//        Student student1 = (Student) applicationContext.getBean("student");
//
//
//        person1.hello("james");

    }
}
