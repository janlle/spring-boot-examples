package com.leone.boot.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author leone
 * @since 2018-05-27
 **/
//@EnableScheduling
@SpringBootApplication(exclude = {

})
public class JobsApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobsApplication.class, args);
    }
}
