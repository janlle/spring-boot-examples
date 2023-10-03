package com.leone.boot.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leone.boot.mybatisplus.entity.User;
import com.leone.boot.mybatisplus.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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

    @PostMapping
    public Boolean save(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/saveBatch")
    public boolean saveBatch(@RequestBody List<User> users) {
        return userService.saveBatch(users);
    }

    @PutMapping
    public boolean update(@RequestBody User user) {
        return userService.update(user, new UpdateWrapper<>());
    }

    @PutMapping("/saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/page")
    public IPage<User> page(@RequestParam(required = false) String account,
                            @RequestParam(defaultValue = "0", required = false) Integer page,
                            @RequestParam(defaultValue = "10", required = false) Integer size) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!Objects.isNull(account)) {
            queryWrapper.like("account", account);
        }
        return userService.page(new Page<>(page, size), queryWrapper);
    }

    @GetMapping("/{userId}")
    public User user(@PathVariable("userId") Long userId) {
        return userService.getById(userId);
    }


    @DeleteMapping("/{userId}")
    public boolean deleteById(@PathVariable("userId") Long userId) {
        return userService.removeById(userId);
    }

}
