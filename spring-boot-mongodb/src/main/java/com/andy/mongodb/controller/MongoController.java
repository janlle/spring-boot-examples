package com.andy.mongodb.controller;

import com.andy.mongodb.entity.User;
import com.andy.mongodb.service.MongoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2019-01-10
 **/
@Slf4j
@RestController
@RequestMapping("/api/mongo")
public class MongoController {

    @Autowired
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
