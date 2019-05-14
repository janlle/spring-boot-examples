package com.leone.boot.mongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * <p>
 *
 * @author leone
 * @since 2018-11-24
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private Boolean enable = true;

    @Bean
    public Docket webApi() {
        Parameter parameter = new ParameterBuilder()
                .name("Authorization")
                .description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue("token ")
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo())
                .groupName("web-API")
                .globalOperationParameters(Collections.singletonList(parameter))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.andy"))
                .paths(s -> true)
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("WEB端接口文档")
                .description("WEB端接口文档,注意传入的参数!")
                .version("v1.0.0")
                .build();
    }

}
