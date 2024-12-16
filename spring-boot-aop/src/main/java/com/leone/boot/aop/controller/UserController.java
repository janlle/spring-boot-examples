package com.leone.boot.aop.controller;

import com.leone.boot.aop.anno.AopBefore;
import com.leone.boot.aop.anno.ClassAop;
import com.leone.boot.aop.anno.LogAop;
import com.leone.boot.aop.anno.TimeWatchAop;
import com.leone.boot.aop.interf.UserService;
import com.leone.boot.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author leone
 * @since 2018-06-21
 **/
@ClassAop
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @AopBefore
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User findOne(@PathVariable Long userId) {
        return userService.findOne(userId);
    }

    @AopBefore
    @GetMapping("/user/save")
    public User save(User user) {
        return user;
    }


    @LogAop(name = "user")
    @GetMapping(value = "/user/delete")
    public String delete(@RequestParam(name = "userId") Long userId) {
        return "delete";
    }

    @TimeWatchAop
    @GetMapping("/user/list")
    public String list(User user) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "list";
    }


}