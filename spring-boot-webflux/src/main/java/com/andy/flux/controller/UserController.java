package com.andy.flux.controller;

import com.andy.flux.entity.User;
import com.andy.flux.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @PostMapping()
//    public Mono<User> save(User user) {
//        return this.userService.save(user);
//    }
//
//    @DeleteMapping("/{username}")
//    public Mono<Long> deleteByUsername(@PathVariable String username) {
//        return this.userService.deleteByUsername(username);
//    }
//
//    @GetMapping("/{username}")
//    public Mono<User> findByUsername(@PathVariable String username) {
//        return this.userService.findByUsername(username);
//    }

    @GetMapping("/all")
    public Flux<User> findAll() {
        return this.userRepository.findAll();
    }

    @GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindAll() {
        return this.userRepository.findAll();
    }


}