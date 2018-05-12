package com.andy.data.controller;

import com.andy.data.entity.User;
import com.andy.data.jpa.repository.UserRepository;
import com.andy.data.mybatis.mapper.UserMapper;
import com.andy.data.service.DataService;
import com.andy.data.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/insertOne")
    public String insertOne(@RequestBody User user) {
        log.info("user:{}", user);
        long start = System.currentTimeMillis();
        int result = userMapper.insert(new User(12L, "james", "admin", new Date(), "andy@gmail.com", 10000 + 0.1, "token:84dsajf823djsi"));
        long end = System.currentTimeMillis();
        return "insertOne一共用了:" + (end - start) + "豪秒！返回的结果是:" + result;
    }

    @GetMapping("/insertList")
    public String insertList() {
        long start = System.currentTimeMillis();
        List<User> users = DataService.getUsers();
        int result = userMapper.insertList(users);
        long end = System.currentTimeMillis();
        return "insertList批量插入"+ users.size() +"条数据一共用了:" + (end - start) + "豪秒！返回的结果是:" + result;
    }

    @GetMapping("/insertForeach")
    public String insertForeach() {
        long start = System.currentTimeMillis();
        for (long i = 0; i < 10000; i++) {
            userMapper.insert(new User(i, "james" + i, "admin" + i, new Date(), "andy@gmail.com", 10000 + 0.1, "token:84dsajf823djsi"));
        }
        long end = System.currentTimeMillis();
        return "insertForeach循环插入10000条数据一共用了:" + (end - start) + "豪秒!";
    }


    @PostMapping("/update")
    public String update(@RequestBody User user) {
        long start = System.currentTimeMillis();
        int result = userMapper.updateById(user);
        long end = System.currentTimeMillis();
        return "update1条数据一共用了:" + (end - start) + "豪秒!返回的结果是:" + result;
    }


    @GetMapping("/deleteById")
    public String deleteById(Long id) {
        long start = System.currentTimeMillis();
        int result = userMapper.deleteById(id);
        long end = System.currentTimeMillis();
        return "deleteById1条数据一共用了:" + (end - start) + "豪秒!返回的结果是:" + result;
    }

    @GetMapping("/selectList")
    public String selectList() {
        long start = System.currentTimeMillis();
        List<User> result = userMapper.selectAll();
        long end = System.currentTimeMillis();
        return "selectList查询了:"+result.size()+"条数据一共用了:" + (end - start) + "豪秒!";
    }


    @GetMapping("/selectById")
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    @GetMapping("/insertUserMybaits")
    public String insertUserMybaits() {
        long start = System.currentTimeMillis();
        userService.insertUserMybaits(10000);
        long end = System.currentTimeMillis();
        return "insertUserMybaits循环插入10000条数据一共用了:" + (end - start) + "豪秒!";
    }



}
