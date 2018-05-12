package com.andy.flux.controller;//package com.andy.webflux.controller;
//
//import com.andy.webflux.entity.User;
//import com.andy.webflux.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
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
//
//    @GetMapping()
//    public Flux<User> findAll() {
//        return this.userService.findAll();
//    }
//}