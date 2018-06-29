package com.andy.spring;

import com.andy.spring.beans.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-29 23:18
 **/
public class App {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        System.out.println(applicationContext.getApplicationName());
        System.out.println(applicationContext.getDisplayName());
        System.out.println(applicationContext.getId());
        System.out.println(applicationContext.getStartupDate());

        Person person1 = (Person) applicationContext.getBean("person");

        person1.hello("james");


    }
}
