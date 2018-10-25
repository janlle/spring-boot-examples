package com.andy.data.controller;

import com.andy.data.entity.User;
import com.andy.data.mybatis.UserMapper;
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
        return "修改1条数据一共用了:" + result + " 豪秒!返回的结果是:" + result;
    }

    @GetMapping("/delete")
    public String deleteById(Long id) {
        int result = userMapper.deleteById(id);
        return "删除1条数据一共用了:" + result + "豪秒!返回的结果是:" + result;
    }

    @GetMapping("/list")
    public String selectList() {
        List<User> result = userMapper.findAll();
        return "selectList查询了:" + result.size() + "条数据一共用了:" + result + "豪秒!";
    }

    @GetMapping("/user")
    public User selectById(Long id) {
        return userMapper.findByUserId(id);
    }

    @GetMapping("/batchInsert")
    public String insertList(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = myBatisService.insertBatch(count);
        return "batch insert " + count + " expenditure:" + time + " MS！";
    }

    @GetMapping("/foreachInsert")
    public String insertForeach(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = myBatisService.insertForeach(count);
        return "foreach insert " + count + " expenditure:" + time + " MS！";
    }

}
