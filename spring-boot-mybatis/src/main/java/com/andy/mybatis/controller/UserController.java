package com.andy.mybatis.controller;

import com.andy.common.entity.User;
import com.andy.mybatis.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * @author Leone
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
    public List<User> selectList(@RequestParam Integer start, @RequestParam Integer size) {
        return userService.page(start, size);
    }

    @GetMapping("/{userId}")
    public User selectById(@PathVariable("userId") Long userId) {
        return userService.selectById(userId);
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
