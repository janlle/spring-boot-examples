package com.leone.boot.mybatis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leone.boot.mybatis.entity.User1;
import com.leone.boot.mybatis.mapper.User1Mapper;
import com.leone.boot.mybatis.service.User1Service;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-13
 **/
@RestController
@RequestMapping("/plus")
public class PlusController {

    @Resource
    private User1Service goodsService;

    @Resource
    private User1Mapper user1Mapper;

    @PostMapping
    public Boolean save(@RequestBody User1 User1) {
        return goodsService.save(User1);
    }

    @PostMapping("/saveBatch")
    public boolean saveBatch(@RequestBody List<User1> goods) {
        return goodsService.saveBatch(goods);
    }

    @PutMapping
    public boolean update(@RequestBody User1 user1) {
        return goodsService.update(user1, new UpdateWrapper<>());
    }

    @PutMapping("/saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody User1 user1) {
        return goodsService.saveOrUpdate(user1);
    }

    @GetMapping("/list")
    public List<User1> list() {
        return goodsService.list();
    }

    @GetMapping("/page")
    public IPage<User1> page(@RequestParam(required = false) String goodsName,
                             @RequestParam(defaultValue = "0", required = false) Integer page,
                             @RequestParam(defaultValue = "10", required = false) Integer size) {
        QueryWrapper<User1> queryWrapper = new QueryWrapper<>();
        if (!Objects.isNull(goodsName)) {
            queryWrapper.like("goods_name", goodsName);
        }
        return goodsService.page(new Page<>(page, size), queryWrapper);
    }

    @GetMapping("/{id}")
    public Object findById(@PathVariable("id") Long id) {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            User1 user1 = user1Mapper.selectById(id);
        }
        return System.currentTimeMillis() - l;
    }

}
