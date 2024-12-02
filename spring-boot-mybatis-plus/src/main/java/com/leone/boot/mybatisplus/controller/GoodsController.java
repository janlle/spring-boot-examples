package com.leone.boot.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leone.boot.mybatisplus.entity.Goods;
import com.leone.boot.mybatisplus.service.IGoodsService;
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
@RequestMapping("/api/goods")
public class GoodsController {

    @Resource
    private IGoodsService goodsService;

    @PostMapping
    public Boolean save(@RequestBody Goods Goods) {
        return goodsService.save(Goods);
    }

    @PostMapping("/saveBatch")
    public boolean saveBatch(@RequestBody List<Goods> goods) {
        return goodsService.saveBatch(goods);
    }

    @PutMapping
    public boolean update(@RequestBody Goods goods) {
        return goodsService.update(goods, new UpdateWrapper<>());
    }

    @PutMapping("/saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody Goods goods) {
        return goodsService.saveOrUpdate(goods);
    }

    @GetMapping("/list")
    public List<Goods> list() {
        return goodsService.list();
    }

    @GetMapping("/page")
    public IPage<Goods> page(@RequestParam(required = false) String goodsName,
                             @RequestParam(defaultValue = "0", required = false) Integer page,
                             @RequestParam(defaultValue = "10", required = false) Integer size) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if (!Objects.isNull(goodsName)) {
            queryWrapper.like("goods_name", goodsName);
        }
        return goodsService.page(new Page<>(page, size), queryWrapper);
    }

    @GetMapping("/{goodsId}")
    public Goods findById(@PathVariable("goodsId") Long goodsId) {
        return goodsService.getById(goodsId);
    }


    @DeleteMapping("/{goodsId}")
    public boolean deleteById(@PathVariable("goodsId") Long goodsId) {
        return goodsService.removeById(goodsId);
    }

}
