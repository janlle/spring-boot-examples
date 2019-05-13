package com.leone.mybatis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leone.mybatis.entity.User;
import com.leone.mybatis.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-13
 **/
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/{userId}")
    public User user(@PathVariable("userId") Integer userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return userService.getOne(queryWrapper);
    }

}
