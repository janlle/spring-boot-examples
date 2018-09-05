package com.andy.flux.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lyon
 * @since: 2018-05-21
 **/
@Component
public class HelloHandler {

    public Mono<ServerResponse> helloMono(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("hello world"));
    }

//    public Flux<ServerResponse> helloFlux(ServletRequest request) {
//        Map<String, Integer> map = new HashMap<>();
//        map.put("hello", 200);
//        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
//                .body(BodyInserters.fromObject(map));
//    }

}