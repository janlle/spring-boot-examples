package com.leone.boot.flux.controller;

import com.leone.boot.common.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

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

//    @GetMapping("/all")
//    public Flux<User> findAll() {
//        return this.userRepository.findAll();
//    }
//
//    @GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<User> streamFindAll() {
//        return this.userRepository.findAll();
//    }

//    @PostMapping
//    public Mono<User> save(@RequestBody User user) {
//        return this.userRepository.save(user);
//    }

//    @DeleteMapping("/{id}")
//    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
//        return this.userRepository.findById(id)
//                // 操作数据并返回 使用flatMap
//                // 只是操作数据不返回使用map
//                .flatMap(user -> this.userRepository.delete(user).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
//                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

//    @PutMapping("/{id}")
//    public Mono<ResponseEntity<User>> update(@PathVariable String id, @RequestBody User user) {
//        return this.userRepository.findById(id)
//                .flatMap(u -> {
//                    u.setAge(user.getAge());
//                    u.setPassword(user.getPassword());
//                    u.setPassword(user.getPassword());
//                    return this.userRepository.save(u);
//                }).map(u -> new ResponseEntity<User>(HttpStatus.OK))
//                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

//    @GetMapping("/{id}")
//    public Mono<ResponseEntity<User>> get(@PathVariable String id) {
//        return this.userRepository.findById(id).map(u -> new ResponseEntity<User>(HttpStatus.OK))
//                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

}