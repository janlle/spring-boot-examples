package com.andy.data.controller;

import com.andy.data.entity.User;
import com.andy.data.mybatis.mapper.UserMapper;
import com.andy.data.service.JpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-05-11 19:30
 **/
@Slf4j
@RestController
@RequestMapping("/mybatis")
public class MybatisController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JpaService userService;

    @PostMapping("/update")
    public String update(@RequestBody User user) {
        long start = System.currentTimeMillis();
        int result = userMapper.updateById(user);
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
        List<User> result = userMapper.selectAll();
        long end = System.currentTimeMillis();
        return "selectList查询了:"+result.size()+"条数据一共用了:" + (end - start) + "豪秒!";
    }

    @GetMapping("/user")
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    @GetMapping("/batchInsert")
    public String insertList(@RequestParam(required = false, defaultValue = "1") Integer count) {
        List<User> list = new ArrayList<>();
        for (long i = 0; i < count; i++) {
            list.add(new User("james" + i, "james" + i, new Date(), 1000.0 + i, new Date(), false));
        }
        long start = System.currentTimeMillis();
        userMapper.insertList(list);
        long end = System.currentTimeMillis();
        return "批量插入" + count + "条数据一共用了:" + (end - start) + "豪秒！";
    }

    @GetMapping("/foreachInsert")
    public String insertForeach(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long start = System.currentTimeMillis();
        for (long i = 0; i < count; i++) {
            userMapper.insert(new User("james" + i, "james" + i, new Date(), 1000.0 + i, new Date(), false));
        }
        long end = System.currentTimeMillis();
        return "循环插入" + count + "条数据一共用了:" + (end - start) + "豪秒!";
    }

}
