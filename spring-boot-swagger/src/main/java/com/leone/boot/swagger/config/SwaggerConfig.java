//package com.leone.boot.swagger.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.ParameterBuilder;
//import springfox.documentation.schema.ModelRef;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.Parameter;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
//
///**
// * @author leone
// * @since 2018-07-12
// **/
//@Component
//public class SwaggerConfig {
//
//    @Value("${swagger.show}")
//    private Boolean enable;
//
//    @Bean
//    public Docket swaggerApi() {
//        Parameter parameter = new ParameterBuilder()
//                .name("Authorization")
//                .description("token")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false)
//                .defaultValue("token ")
//                .build();
//        return new Docket(DocumentationType.SWAGGER_2)
//                .enable(enable)
//                .apiInfo(apiInfo())
//                .groupName("web-API")
//                .select()
//                .apis(basePackage("com.andy"))
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("API")
//                .description("web接口文档")
//                .version("v1.0.0")
//                .termsOfServiceUrl("http://www.baidu.com")
//                .contact(new Contact("leone", "https://leone.com", "leone@gmail.com"))
//                .license("Apache2.0")
//                .licenseUrl("http://www.apache.org")
//                .build();
//    }
//}
