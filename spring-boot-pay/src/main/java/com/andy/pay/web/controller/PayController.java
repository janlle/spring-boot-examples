package com.andy.pay.web.controller;

import com.andy.pay.common.Result;
import com.andy.pay.common.enums.ResultEnum;
import com.andy.pay.object.entity.User;
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
@RestController
@Api(tags ="支付回调接口")
@RequestMapping(value = "/pay/notify")
public class PayController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "微信回调接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/weChatNotify")
    public Result<String> weChatNotify() {
        return Result.success("收到微信支付回调！");
    }


}
