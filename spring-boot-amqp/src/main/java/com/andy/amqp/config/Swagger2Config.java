package com.andy.amqp.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class Swagger2Config {
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