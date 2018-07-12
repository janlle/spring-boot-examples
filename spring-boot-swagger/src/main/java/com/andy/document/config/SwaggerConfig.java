package com.andy.document.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-12 20:12
 **/
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket getApiInfo() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("outer api")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API")
                .description("接口")
                .version("1.0")
                .termsOfServiceUrl("http://www.baidu.com")
                .contact(new Contact("", "", ""))
                .license("Apache2.0")
                .licenseUrl("http://www.apache.org")
                .build();
    }

    @Bean
    public WebMvcConfigurer coreConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowedOrigins("*");
            }
        };
    }

    @Bean
    public UiConfiguration getUiConfig() {
        return new UiConfiguration(
                null,// url,暂不用
                "none",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                false,        // enableJsonEditor      => true | false
                true);        // showRequestHeaders    => true | false
    }


//
//    @Bean
//    public Docket swaggerSpringMvcPlugin() {
//        Parameter parameter = new ParameterBuilder()
//                .name("Authorization")
//                .description("token")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false)
//                .defaultValue("token ")
//                .build();
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .globalOperationParameters(Collections.singletonList(parameter))
//                .select()
//                .apis(basePackage("com.luwei.controllers"))
//                .paths(paths())
//                .build();
//    }
//
//    private Predicate<String> paths() {
//        return Predicates.alwaysTrue();
//    }
//


}
