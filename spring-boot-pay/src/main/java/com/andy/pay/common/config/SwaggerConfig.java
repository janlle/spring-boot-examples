package com.andy.pay.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-4-15 23:16
 **/
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket webApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("支付服务API接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.andy.pay.web"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket alipayApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("支付宝API接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.andy.pay.ali"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket weixinpayApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("微信API接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.andy.pay.weixin"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket unionpayApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("银联API接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.andy.pay.unionpay"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("支付系统")
                .description("微信、支付宝、银联支付服务")
                .termsOfServiceUrl("https://xxx.xxx.com")
                .version("v1.0.1")
                .build();
//				.contact(new Contact("andy", "https://andy.com", "andy@163.com"));
    }

}