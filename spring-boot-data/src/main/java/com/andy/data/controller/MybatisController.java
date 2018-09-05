package com.andy.data.controller;

import com.andy.data.entity.User;
import com.andy.data.mybatis.mapper.UserMapper;
import com.andy.data.service.MyBatisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Leone
 * @since: 2018-05-11
 **/
@Slf4j
@RestController
@RequestMapping("/mybatis")
public class MybatisController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MyBatisService myBatisService;

    @PutMapping("/update")
    public String update(@RequestBody User user) {
        long start = System.currentTimeMillis();
        int result = myBatisService.update(user);
        long end = System.currentTimeMillis();
        return "修改1条数据一共用了:" + (end - start) + "豪秒!返回的结果是:" + result;
    }

    @GetMapping("/delete")
    public String deleteById(Long id) {
        long start = System.currentTimeMillis();
        int result = userMapper.deleteById(id);
        long end = System.currentTimeMillis();
        return "删除1条数据一共用了:" + (end - start) + "豪秒!返回的结果是:" + result;
    }

    @GetMapping("/list")
    public String selectList() {
        long start = System.currentTimeMillis();
        List<User> result = userMapper.findAll();
        long end = System.currentTimeMillis();
        return "selectList查询了:" + result.size() + "条数据一共用了:" + (end - start) + "豪秒!";
    }

    @GetMapping("/user")
    public User selectById(Long id) {
        return userMapper.findByUserId(id);
    }

    @GetMapping("/batchInsert")
    public String insertList(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = myBatisService.insertList(count);
        return "批量插入" + count + "条数据一共用了:" + time + "豪秒！";
    }

    @GetMapping("/foreachInsert")
    public String insertForeach(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = myBatisService.insertForeach(count);
        return "批量插入" + count + "条数据一共用了:" + time + "豪秒！";
    }

}
