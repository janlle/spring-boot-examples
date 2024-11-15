package com.leone.test;

import com.leone.boot.quartz.JobsApplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

/**
 * <p>
 *
 * @author lyon
 * @since 2018-08-21
 **/
@SpringBootTest(classes = JobsApplication.class)

public class SpringBootTests {

    @Autowired
    private Environment environment;

    @Value("${hello.world}")
    private String message;

    
    public void testEnv() {
        System.out.println(message);
    }

    
    public void testEnvironment() {
        System.out.println(environment.getProperty("hello.world"));
    }

}
