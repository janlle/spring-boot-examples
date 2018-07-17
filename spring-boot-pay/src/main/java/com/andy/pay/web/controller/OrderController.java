package com.andy.pay.web.controller;

import com.andy.pay.common.Result;
import com.andy.pay.common.enums.ResultEnum;
import com.andy.pay.common.exception.AppException;
import com.andy.pay.pojos.dto.OrderDto;
import com.andy.pay.pojos.form.OrderForm;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@Api(tags ="订单操作接口")
@RequestMapping(value = "/api/order")
public class OrderController {


    //创建订单
    @PostMapping("/create")
    public Result<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确，orderForm={}", orderForm);
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        return Result.success(null);
    }

    //订单列表
    @GetMapping("/list")
    public Result<List<OrderDto>> list(@RequestParam("openid")String openid,
                                       @RequestParam(value = "page", defaultValue = "0")Integer page,
                                       @RequestParam(value = "size", defaultValue = "10")Integer size){
        if (StringUtils.isEmpty(openid)) {
            log.error("[查询订单]参数传递错误");
            throw new AppException(ResultEnum.PARAM_ERROR);
        }
        
        return Result.success(null);
    }

    //订单详情
    @GetMapping("/detail")
    public Result<List<OrderDto>> detail(@RequestParam("orderId") String orderId,
                                           @RequestParam("openid")String openid,
                                           @RequestParam(value = "page", defaultValue = "0")Integer page,
                                           @RequestParam(value = "size", defaultValue = "10")Integer size){

        return Result.success(null);
    }

    //取消订单
    @GetMapping("/cancel")
    public Result<List<OrderDto>> detail(@RequestParam("orderId") String orderId,
                                           @RequestParam("openid")String openid){
        return Result.success(null);
    }


}
