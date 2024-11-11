package com.leone.boot.concurrency;

import com.leone.boot.concurrency.test.HttpFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * @author leone
 * @since 2018-04-22
 **/
@Configuration
@SpringBootApplication
public class ConcurrencyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean httpFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new HttpFilter());
        registrationBean.addUrlPatterns("/threadLocal/*");
        return registrationBean;
    }

    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }
}
