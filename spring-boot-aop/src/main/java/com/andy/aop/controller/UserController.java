package com.andy.aop.controller;

import com.andy.aop.entity.User;
import com.andy.aop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Leone
 * @since 2018-06-21
 **/
@Slf4j
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User hello(@PathVariable Long id) {
        return userService.user(id);
    }

}