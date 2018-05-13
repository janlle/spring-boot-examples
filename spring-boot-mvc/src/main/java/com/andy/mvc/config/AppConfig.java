package com.andy.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-03 18:58
 **/
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate (){
        return new RestTemplate();
    }

}
