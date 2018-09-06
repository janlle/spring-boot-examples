package com.andy.data.controller;

import com.andy.data.entity.User;
import com.andy.data.jpa.repository.UserRepository;
import com.andy.data.service.JpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Leone
 * @since 2018-05-11 19:30
 **/
@Slf4j
@RestController
@RequestMapping("/jpa")
public class JpaController {

    @Autowired
    private JpaService jpaService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/batchInsert")
    public String insertList(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = jpaService.insertList(count);
        return "批量插入" + count + "条数据一共用了:" + time + "豪秒！";
    }

    @GetMapping("/foreachInsert")
    public String insertForeach(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = jpaService.insertForeach(count);
        return "批量插入" + count + "条数据一共用了:" + time + "豪秒！";
    }


    @PutMapping("/update")
    public String update(@RequestBody User user) {
        long start = System.currentTimeMillis();
        int result = jpaService.update(user);
        long end = System.currentTimeMillis();
        return "修改1条数据一共用了:" + (end - start) + "豪秒!返回的结果是:" + result;
    }

//    @InitBinder
//    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
//        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//        CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
//        binder.registerCustomEditor(Date.class, dateEditor);
//    }

}
