package com.leone.boot.flux.controller;

import com.leone.boot.common.aop.Watch;
import com.leone.boot.common.entity.User;
import com.leone.boot.flux.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api")
public class TestController {

    private UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @Watch
    @GetMapping("/mono")
    public Mono<String> mono() {
        return Mono.fromSupplier(() -> "mono " + job(3000));
    }

    @Watch
    @GetMapping(value = "flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() {
        return Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> "flux " + job(1000)));
    }

    @Watch
    @GetMapping("list")
    public Flux<User> userList() {
        return userService.getUsers();
    }

    @GetMapping("/flux/user/{id}")
    public Mono<User> user(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    /**
     * 模拟耗时操作的任务
     *
     * @param time
     * @return
     */
    private String job(long time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "job process success expenditure " + time + " milliseconds";
    }

}
