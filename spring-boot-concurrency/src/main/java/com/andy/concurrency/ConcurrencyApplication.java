package com.andy.concurrency;

import com.andy.concurrency.test.HttpFilter;
import com.andy.concurrency.test.HttpInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: Mr.ruoLin
 * @since: 2018-04-22 15:13
 **/
@Configuration
@SpringBootApplication
public class ConcurrencyApplication extends WebMvcConfigurerAdapter {
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }
}
