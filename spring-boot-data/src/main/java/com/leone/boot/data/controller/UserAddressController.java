package com.leone.boot.data.controller;

import com.leone.boot.data.jpa.entity.UserAddress;
import com.leone.boot.data.jpa.repository.UserAddressRepository;
import com.leone.boot.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author leone
 * @since 2018-05-11
 **/
@RestController
@RequestMapping("/api/jpa")
public class UserAddressController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAddressRepository userAddressRepository;

    @GetMapping("/batchInsert")
    public String insertBatch(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = userService.jpaInsertBatch(count);
        return "batch insert " + count + " expenditure:" + time + " ms!";
    }

    @GetMapping("/insert")
    public String insert(@RequestParam(name = "count", required = false, defaultValue = "1") Integer count) {
        long time = userService.insertUserAddress(count);
        return "insert " + count + " expend:" + time + " ms!";
    }

    @PutMapping("/update")
    public String update(@RequestBody UserAddress user) {
        int result = userService.jpaUpdate(user);
        return "update one expenditure:" + result + " ms!";
    }

}
