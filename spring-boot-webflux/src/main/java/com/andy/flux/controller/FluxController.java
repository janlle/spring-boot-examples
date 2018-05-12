package com.andy.flux.controller;

import com.andy.flux.entity.User;
import com.andy.flux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FluxController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello-mono")
    public Mono<String> sayHelloWorld() {
        return Mono.just("Hello-mono");
    }

    @GetMapping("hello-flux")
    public Flux<String> helloFlux() {
        return Flux.just("hello-flux");
    }

    @GetMapping("list")
    public Flux<User> userList() {
        return userService.getUsers();
    }

    @GetMapping("user/{id}")
    public Mono<User> user(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }
}
