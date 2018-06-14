package com.andy.jms;

import com.google.common.base.Predicate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-10 14:50
 **/
@EnableSwagger2
@SpringBootApplication
public class JMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(JMSApplication.class, args);
    }

    @Bean
    public Docket selfCareApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("swagger")
                .apiInfo(selfCareApiInfo())
                .select()
                .paths(selfCareApiPaths())
                .build();
    }

    private ApiInfo selfCareApiInfo() {
        return new ApiInfoBuilder()
                .title("Test Swagger API")
                .version("1.0-SNAPSHOT")
                .build();
    }

    private Predicate<String> selfCareApiPaths() {
        return or(regex("/.*"));
    }

}
