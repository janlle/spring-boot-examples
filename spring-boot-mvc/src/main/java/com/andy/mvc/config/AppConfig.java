package com.andy.mvc.config;

import com.andy.mvc.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Leone
 * @since: 2018-04-03 18:58
 **/
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ProductService beanWayService() {
        return new ProductService();
    }

}
