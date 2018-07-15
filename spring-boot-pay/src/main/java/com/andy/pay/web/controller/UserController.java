package com.andy.pay.web.controller;

import com.andy.pay.common.Result;
import com.andy.pay.common.enums.ResultEnum;
import com.andy.pay.pojos.entity.User;
import com.andy.pay.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Controller
@Api(tags ="订单操作接口")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    private String emailReg = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private String phoneReg = "^[1][3456789]\\d{9}$";

    @ApiOperation(value = "测试", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/")
    public String home() {
        return "success";
    }

    @ApiOperation(value = "用户登录接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",value ="用户账号",required = true, dataType = "String"),
            @ApiImplicitParam(name = "password",value ="用户密码",required = true, dataType = "String")
    })
    @PostMapping("/login")
    public Result<Object> login(String account, String password, HttpServletRequest request, HttpServletResponse response) {

        return Result.build(ResultEnum.USERNAME_PASSWORD_FAIL, null);
    }

    @ApiOperation(value = "用户退出接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value ="用户ID",required = true, dataType = "String"),
            @ApiImplicitParam(name = "token",value ="token",required = true, dataType = "String")
    })
    @GetMapping("/logout")
    public Result<String> logout(String token, Integer userId) {
        log.info("logout [删除token成功]->{}", token);
        return Result.success(20000);
    }

    @ApiOperation(value = "用户注册接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "user",value ="用户信息模型",required = true, dataType = "User")
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {

        return Result.error(50000);
    }

    @ApiOperation(value = "根据用户id获取用户信息", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "userId",value ="用户ID",required = true, dataType = "String")
    @GetMapping("/{userId}")
    public Result<User> user(@PathVariable("userId") String userId) {
        return Result.success(null);
    }

    @ApiOperation(value = "用户认证授权接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value ="用户ID",required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "token",value ="token",required = true, dataType = "String")
    })
    @GetMapping("/authentication")
    public Result<String> verifyToken(String token, Integer userId, HttpServletRequest request, HttpServletResponse response) {

        return Result.error(50000);
    }



}
