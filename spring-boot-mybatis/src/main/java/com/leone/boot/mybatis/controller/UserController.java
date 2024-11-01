package com.leone.boot.mybatis.controller;

import com.github.pagehelper.PageInfo;
import com.leone.boot.common.entity.User;
import com.leone.boot.mybatis.service.UserService;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2018-03-02
 **/
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    public int insert(@RequestBody User user) {
        return userService.insert(user);
    }

    @PostMapping("/batch")
    public int insertBatch(@RequestBody List<User> user) {
        return userService.insertBatch(user);
    }

    @DeleteMapping
    public int deleteById(@RequestParam Long userId) {
        return userService.delete(userId);
    }

    @DeleteMapping("/batch")
    public int deleteByIds(@RequestParam List<Long> userIds) {
        return userService.deleteByIds(userIds);
    }

    @GetMapping("/page")
    public PageInfo<User> page(@RequestParam Integer page, @RequestParam Integer size) {
        return userService.page(page, size);
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/{userId}")
    public User findOne(@PathVariable("userId") Long userId) {
        return userService.findOne(userId);
    }

    @PutMapping
    public int update(@RequestBody User user) {
        return userService.update(user);
    }

    @PutMapping("/batch")
    public int updateBatch(@RequestBody List<User> users) {
        return userService.updateBatch(users);
    }

}
