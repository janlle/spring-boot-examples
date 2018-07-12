package com.andy.swagger;

import com.andy.swagger.entity.UserForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-12 22:40
 **/
@RestController
@Api(value = "文档接口:crud测试")
@RequestMapping("/api/web/user")
public class ApiController {

    @ApiOperation(value = "查询用户列表")
    @GetMapping("/list")
    public String list() {
        return "list";
    }

    @ApiOperation(value = "保存用户")
    @PostMapping
    public String save(@RequestBody UserForm userForm) {
        return "save";
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping
    public String delete() {
        return "delete";
    }

    @ApiOperation(value = "修改用户")
    @PutMapping
    public String update() {
        return "update";
    }

}
