package com.andy.mvc;

import com.andy.mvc.config.CustomerProperty;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

//扫描servlet filter listener
@EnableScheduling
@ServletComponentScan
@MapperScan("com.andy.mvc.dao.mapper")
@SpringBootApplication
public class MVCApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(MVCApplication.class, args);

        Binder binder = Binder.get(applicationContext.getEnvironment());

        List<CustomerProperty> customerProperties = binder.bind("customer.property", Bindable.listOf(CustomerProperty.class)).orElseThrow(IllegalStateException::new);

        System.out.println(customerProperties.get(0));
        System.out.println(customerProperties.get(1));


    }

}
