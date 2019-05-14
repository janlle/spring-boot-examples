package com.leone.boot.swagger.controller;

import com.leone.boot.swagger.entity.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @author leone
 * @since 2018-07-12
 **/
@RestController
@Api(tags = "文档接口:crud测试")
@RequestMapping("/api/user")
public class ApiController {

    @ApiOperation("查询用户列表")
    @GetMapping("/list")
    public String list(@ApiParam("当前页") @RequestParam Integer page, @RequestParam Integer size) {
        return "list";
    }

    @ApiOperation("保存用户")
    @PostMapping
    public String save(@RequestBody UserDTO userForm) {
        return "save";
    }

    @ApiOperation("删除用户")
    @DeleteMapping
    public String delete() {
        return "delete";
    }

    @ApiOperation("修改用户")
    @PutMapping
    public String update() {
        return "update";
    }

}
