package com.andy.pay.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@Api(tags ="订单操作接口")
@RequestMapping(value = "/product")
public class ProductController {


    @ApiOperation("")
    @ApiImplicitParam(name = "", value = "", dataType = "", required = true, paramType = "")
    @PostMapping("/add")
    public String index() {
        return "index";
    }


}
