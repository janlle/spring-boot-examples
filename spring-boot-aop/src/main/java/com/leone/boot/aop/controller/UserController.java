package com.leone.boot.aop.controller;

import com.leone.boot.aop.anno.AopBefore;
import com.leone.boot.aop.anno.ClassAop;
import com.leone.boot.aop.interf.UserService;
import com.leone.boot.common.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leone
 * @since 2018-06-21
 **/
@Slf4j
@ClassAop
@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @AopBefore
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User findOne(@PathVariable Long userId) {
        return userService.findOne(userId);
    }

    @AopBefore
    @RequestMapping("/user")
    public User save(User user) {
        return user;
    }

}