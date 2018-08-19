package com.andy.jvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Leone
 * @since: 2018-07-14 13:14
 **/
@SpringBootApplication
public class JVMApplication {

    public static void main(String[] args) {
        SpringApplication.run(JVMApplication.class, args);
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                System.out.println(args[i]);
            }
        }
    }


}
