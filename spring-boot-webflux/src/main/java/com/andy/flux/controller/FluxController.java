package com.andy.flux.controller;

import com.andy.flux.entity.User;
import com.andy.flux.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
@RestController
public class FluxController {

    @Autowired
    private UserService userService;

    @GetMapping("/mvc")
    public String mvc() {
        log.info("start:{}", System.currentTimeMillis());
        job(3000);
        log.info("end:{}", System.currentTimeMillis());
        return "Hello-mvc";
    }

    @GetMapping("/mono")
    public Mono<String> mono() {
        log.info("start:{}", System.currentTimeMillis());
        Mono<String> result = Mono.fromSupplier(() -> job(3000));
        log.info("end:{}", System.currentTimeMillis());
        return result;
    }

    @GetMapping(value = "flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() {
        log.info("start:{}", System.currentTimeMillis());
        Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "flux data-" + i;
        }));
        log.info("end:{}", System.currentTimeMillis());
        return result;
    }

    @GetMapping("list")
    public Flux<User> userList() {
        return userService.getUsers();
    }

    @GetMapping("/flux/user/{id}")
    public Mono<User> user(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    private String job(long time) {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello world";
    }

}
