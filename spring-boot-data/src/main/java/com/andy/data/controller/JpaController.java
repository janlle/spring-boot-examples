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
import java.util.ArrayList;
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

    @GetMapping("/batchInsert")
    public String insertList(@RequestParam(required = false, defaultValue = "1") Integer count) {
        List<User> list = new ArrayList<>();
        for (long i = 0; i < count; i++) {
            list.add(new User("james" + i, "james" + i, new Date(), 1000.0 + i, new Date(), false));
        }
        long start = System.currentTimeMillis();
        userRepository.saveAll(list);
        long end = System.currentTimeMillis();
        return "批量插入" + count + "条数据一共用了:" + (end - start) + "豪秒！";
    }

    @GetMapping("/foreachInsert")
    public String insertForeach(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long start = System.currentTimeMillis();
        for (long i = 0; i < count; i++) {
            userRepository.save(new User("james" + i, "james" + i, new Date(), 1000.0 + i, new Date(), false));
        }
        long end = System.currentTimeMillis();
        return "循环插入" + count + "条数据一共用了:" + (end - start) + "豪秒!";
    }

//    @InitBinder
//    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
//        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//        CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
//        binder.registerCustomEditor(Date.class, dateEditor);
//    }

}
