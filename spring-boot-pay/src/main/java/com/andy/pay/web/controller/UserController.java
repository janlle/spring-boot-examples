package com.andy.pay.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@Api(tags ="订单操作接口")
@RequestMapping(value = "/order")
public class UserController {


    @ApiOperation("")
    @ApiImplicitParam(name = "", value = "", dataType = "", required = true, paramType = "")
    @RequestMapping("/")
    public String index() {
        return "index";
    }


}
