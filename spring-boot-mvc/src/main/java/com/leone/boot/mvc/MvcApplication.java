package com.leone.boot.mvc;

import com.leone.boot.mvc.config.CustomerProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2018-05-17
 **/
//@EnableRedisHttpSession
@EnableScheduling
@ServletComponentScan
@SpringBootApplication
public class MvcApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MvcApplication.class, args);
        Binder binder = Binder.get(applicationContext.getEnvironment());
        List<CustomerProperty> customerProperties = binder.bind("customer.property", Bindable.listOf(CustomerProperty.class)).orElseThrow(IllegalStateException::new);
        System.out.println(customerProperties.get(0));
        System.out.println(customerProperties.get(1));
    }
}
