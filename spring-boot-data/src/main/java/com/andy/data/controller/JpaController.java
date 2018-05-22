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
@RequestMapping("/jpa")
public class JpaController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/insertOne")
    public String insertOne(@RequestBody User user) {
        log.info("user:{}", user);
        long start = System.currentTimeMillis();
        userRepository.save(user);
        long end = System.currentTimeMillis();
        return "insertOne一共用了:" + (end - start) + "豪秒!";
    }

    @GetMapping("/insertList")
    public String insertList() {
        long start = System.currentTimeMillis();
        List<User> users = DataService.getUsers();
//        userRepository.saveAll(users);
        long end = System.currentTimeMillis();
        return "insertList批量插入"+ users.size() +"条数据一共用了:" + (end - start) + "豪秒！";
    }

    @GetMapping("/insertForeach")
    public String insertForeach() {
        long start = System.currentTimeMillis();
        for (long i = 0; i < 10000; i++) {
            userRepository.save(new User(i, "james" + i, "admin" + i, new Date(), "andy@gmail.com", 10000 + 0.1, "token:84dsajf823djsi"));
        }
        long end = System.currentTimeMillis();
        return "insertForeach循环插入10000条数据一共用了:" + (end - start) + "豪秒!";
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
        userService.insertUserJpa(10000);
        long end = System.currentTimeMillis();
        return "insertUserMybaits循环插入10000条数据一共用了:" + (end - start) + "豪秒!";
    }



}
