package com.leone.boot.mybatis.controller;

import com.leone.boot.mybatis.entity.User2;
import com.leone.boot.mybatis.mapper.User2Mapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-13
 **/
@RestController
@RequestMapping("/flex")
public class FlexController {

    @Resource
    private User2Mapper user2Mapper;

    @GetMapping("/{id}")
    public Object findById(@PathVariable("id") Long id) {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            User2 user2 = user2Mapper.selectOneById(id);
        }
        return System.currentTimeMillis() - l;
    }


}
