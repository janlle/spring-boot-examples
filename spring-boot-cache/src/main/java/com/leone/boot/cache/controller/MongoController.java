package com.leone.boot.cache.controller;

import com.leone.boot.cache.mongo.MongoService;
import com.leone.boot.common.entity.User;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2019-01-10
 **/
//@RestController
@RequestMapping("/api/mongo")
public class MongoController {

    @Resource
    private MongoService mongoService;


    @PostMapping
    public User save(@RequestBody User user) {
        return mongoService.save(user);
    }

    @GetMapping("/list")
    public List<User> list() {
        return mongoService.list();
    }


    @GetMapping("/account")
    public User findByName(@RequestParam("account") String account) {
        return mongoService.findByName(account);
    }

    @PutMapping
    public void update(@RequestBody User user) {
        mongoService.update(user);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") Integer userId) {
        mongoService.delete(userId);
    }

}
