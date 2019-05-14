package com.leone.boot.mybatis.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leone.boot.mybatis.entity.User;
import com.leone.boot.mybatis.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/page")
    public IPage<User> page(@RequestParam Integer page, @RequestParam Integer size) {
        return userService.page(new Page<>(page, size));
    }

    @GetMapping("/{userId}")
    public User user(@PathVariable("userId") Long userId) {
        return userService.getById(userId);
    }

    @PostMapping
    public boolean save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping
    public boolean update(@RequestBody User user) {
        return userService.update(user, new UpdateWrapper<>());
    }

    @DeleteMapping("/{userId}")
    public boolean deleteById(@PathVariable("userId") Long userId) {
        return userService.removeById(userId);
    }

    public void test() {

    }


}
