package com.leone.boot.data.controller;

import com.github.pagehelper.PageInfo;
import com.leone.boot.common.entity.User;
import com.leone.boot.data.mybatis.mapper.UserMapper;
import com.leone.boot.data.service.UserService;
import jakarta.annotation.Resource;
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


    //@PostMapping
    //public int insert(@RequestBody User user) {
    //    return userService.insert(user);
    //}
    //
    //@PostMapping("/batch")
    //public int insertBatch(@RequestBody List<User> user) {
    //    return userService.insertBatch(user);
    //}
    //
    //@DeleteMapping
    //public int deleteById(@RequestParam Long userId) {
    //    return userService.delete(userId);
    //}
    //
    //@DeleteMapping("/batch")
    //public int deleteByIds(@RequestParam List<Long> userIds) {
    //    return userService.deleteByIds(userIds);
    //}
    //
    //@GetMapping("/page")
    //public PageInfo<User> page(@RequestParam Integer page, @RequestParam Integer size) {
    //    return userService.page(page, size);
    //}
    //
    //@GetMapping("/list")
    //public List<User> list() {
    //    return userService.list();
    //}
    //
    //@GetMapping("/{userId}")
    //public User findOne(@PathVariable("userId") Long userId) {
    //    return userService.findOne(userId);
    //}
    //
    //@PutMapping
    //public int update(@RequestBody User user) {
    //    return userService.update(user);
    //}
    //
    //@PutMapping("/batch")
    //public int updateBatch(@RequestBody List<User> users) {
    //    return userService.updateBatch(users);
    //}
    //
    //
    //@PostMapping("/insert-user")
    //public int insertUser(@RequestBody User user) {
    //    System.out.println(user);
    //    return userService.insert(user);
    //}
    //
    //@GetMapping("/find-user")
    //public User findUser(@RequestParam String name) {
    //    return userService.selectByName(name);
    //}

}
