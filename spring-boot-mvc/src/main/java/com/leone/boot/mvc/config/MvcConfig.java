package com.leone.boot.mvc.config;

import com.leone.boot.mvc.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.concurrent.Executor;

/**
 * @author leone
 * @since 2018-04-03
 **/
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ProductService beanWayService() {
        return new ProductService();
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(15);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(10);
        executor.initialize();
        return executor;
    }


}
