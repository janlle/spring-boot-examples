package com.andy.data.controller;

import com.andy.data.entity.User;
import com.andy.data.repository.mybatis.UserMapper;
import com.andy.data.service.MybatisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Leone
 * @since 2018-05-11
 **/
@Slf4j
@RestController
@RequestMapping("/mybatis")
public class MybatisController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MybatisService myBatisService;

    @PutMapping("/update")
    public String update(@RequestBody User user) {
        long result = myBatisService.update(user);
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
        long time = myBatisService.insertBatch(count);
        return "batch insert " + count + " expenditure:" + time + " ms!";
    }

    @GetMapping("/foreachInsert")
    public String insertForeach(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = myBatisService.insertForeach(count);
        return "foreach insert " + count + " expenditure:" + time + " ms!";
    }

}
