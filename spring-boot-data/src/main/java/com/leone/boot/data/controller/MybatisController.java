package com.leone.boot.data.controller;

import com.leone.boot.common.entity.User;
import com.leone.boot.data.repository.mybatis.UserMapper;
import com.leone.boot.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author leone
 * @since 2018-05-11
 **/
@RestController
@RequestMapping("/api/mybatis")
public class MybatisController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @PutMapping("/update")
    public String update(@RequestBody User user) {
        long result = userService.mybatisUpdate(user);
        return "update one time:" + result + " ms!";
    }

    @GetMapping("/delete")
    public String deleteById(Long id) {
        int result = userMapper.deleteById(id);
        return "delete one line time:" + result;
    }

    @GetMapping("/list")
    public String selectList() {
        List<User> result = userMapper.findAll();
        return "select all result size:" + result.size() + "expenditure:" + result + " ms!";
    }

    @GetMapping("/user")
    public User selectById(Long id) {
        return userMapper.findByUserId(id);
    }

    @GetMapping("/batchInsert")
    public String insertList(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = userService.mybatisInsertForeach(count);
        return "batch insert " + count + " expenditure:" + time + " ms!";
    }

    @GetMapping("/foreachInsert")
    public String insertForeach(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = userService.mybatisInsertForeach(count);
        return "foreach insert " + count + " expenditure:" + time + " ms!";
    }

}
