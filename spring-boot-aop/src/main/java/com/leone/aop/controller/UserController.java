package com.leone.aop.controller;

import com.leone.aop.interf.UserService;
import com.leone.common.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User hello(@PathVariable Long userId) {
        return userService.findOne(userId);
    }

}