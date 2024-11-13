package com.leone.boot.mvc.config;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import springfox.documentation.builders.*;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.schema.ScalarType;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;


/**
 * @author leone
 * @since 2018-07-12
 **/
//@EnableOpenApi
//@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${swagger.show}")
    private Boolean enable;

    //@Bean
    //public Docket createRestApi() {
    //    //返回文档摘要信息
    //    return new Docket(DocumentationType.OAS_30)
    //      .apiInfo(apiInfo())
    //      .select()
    //      //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
    //      .apis(RequestHandlerSelectors.withMethodAnnotation(Operation.class))
    //      .paths(PathSelectors.any())
    //      .build()
    //      .globalRequestParameters(getGlobalRequestParameters())
    //      .globalResponses(HttpMethod.GET, getGlobalResponseMessage())
    //      .globalResponses(HttpMethod.POST, getGlobalResponseMessage());
    //}
    //
    //private ApiInfo apiInfo() {
    //    return new ApiInfoBuilder()
    //      .title("API")
    //      .description("web接口文档")
    //      .version("v1.0.0")
    //      .termsOfServiceUrl("http://www.baidu.com")
    //      .contact(new Contact("leone", "https://leone.com", "leone@gmail.com"))
    //      .license("Apache2.0")
    //      .licenseUrl("http://www.apache.org")
    //      .build();
    //}
    //
    ////生成全局通用参数
    //private List<RequestParameter> getGlobalRequestParameters() {
    //    List<RequestParameter> parameters = new ArrayList<>();
    //    parameters.add(new RequestParameterBuilder()
    //      .name("appid")
    //      .description("平台id")
    //      .required(true)
    //      .in(ParameterType.QUERY)
    //      .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
    //      .required(false)
    //      .build());
    //    parameters.add(new RequestParameterBuilder()
    //      .name("udid")
    //      .description("设备的唯一id")
    //      .required(true)
    //      .in(ParameterType.QUERY)
    //      .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
    //      .required(false)
    //      .build());
    //    parameters.add(new RequestParameterBuilder()
    //      .name("version")
    //      .description("客户端的版本号")
    //      .required(true)
    //      .in(ParameterType.QUERY)
    //      .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
    //      .required(false)
    //      .build());
    //    return parameters;
    //}
    //
    ////生成通用响应信息
    //private List<Response> getGlobalResponseMessage() {
    //    List<Response> responseList = new ArrayList<>();
    //    responseList.add(new ResponseBuilder().code("404").description("找不到资源").build());
    //    return responseList;
    //}


}
