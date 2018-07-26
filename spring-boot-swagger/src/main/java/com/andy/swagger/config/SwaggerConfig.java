package com.andy.swagger.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

/**
 * @author: Mr.lyon
 * @createBy: 2018-07-12 20:12
 **/
@Component
public class SwaggerConfig {

    @Bean
    public Docket swaggerApi() {
        Parameter parameter = new ParameterBuilder()
                .name("Authorization")
                .description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue("token ")
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("web-API")
                .globalOperationParameters(Collections.singletonList(parameter))
                .select()
                .apis(basePackage("com.andy"))
                .paths(Predicates.alwaysTrue())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API")
                .description("web接口文档")
                .version("v1.0.0")
                .termsOfServiceUrl("http://www.baidu.com")
                .contact(new Contact("lyon", "https://lyon.com", "lyon@gmail.com"))
                .license("Apache2.0")
                .licenseUrl("http://www.apache.org")
                .build();
    }
}
