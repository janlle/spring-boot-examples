package com.andy.flux.router;

import com.andy.flux.handler.HelloWorldHandler;
import com.andy.flux.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private HelloWorldHandler helloWorldHandler;

    @Bean
    public RouterFunction<?> helloRouter() {
        return RouterFunctions.route(RequestPredicates.GET("/hello"), helloWorldHandler::helloWorld);
    }

    @Autowired
    private UserHandler userHandler;

    @Bean
    public RouterFunction<ServerResponse> timerRouter() {
        return RouterFunctions.route(RequestPredicates.GET("/time"), userHandler::getTime)
                .andRoute(RequestPredicates.GET("/date"), userHandler::getDate);
    }

    @Bean
    public RouterFunction<?> routerFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/hello"), helloWorldHandler::helloWorld)
                .andRoute(RequestPredicates.POST("/register"), userHandler::register)
                .andRoute(RequestPredicates.POST("/login"), userHandler::login)
                .andRoute(RequestPredicates.GET("/times"), userHandler::sendTimePerSec);
    }

}


//    @Autowired
//    private UserHandler userHandler;
//
//    @Bean
//    public RouterFunction<ServerResponse> timerRouter() {
//        return RouterFunctions.route(RequestPredicates.GET("/time"), userHandler::getTime)
//                .andRoute(RequestPredicates.GET("/date"), userHandler::getDate);
//    }
//
//    @Bean
//    public RouterFunction<?> routerFunction() {
//        return RouterFunctions.route(RequestPredicates.GET("/hello"), helloWorldHandler::helloWorld)
//                .andRoute(RequestPredicates.POST("/register"), userHandler::register)
//                .andRoute(RequestPredicates.POST("/login"), userHandler::login)
//                .andRoute(RequestPredicates.GET("/times"), userHandler::sendTimePerSec);
//    }