package com.andy.pay.web.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@Api(tags = "订单操作接口")
@RequestMapping(value = "/api/order")
public class OrderController {


//    //创建订单
//    @PostMapping("/create")
//    public Response<Map<String, String>> create(@Valid OrderForm orderForm, BindingResponse bindingResponse){
//
//        return Response.success(null);
//    }
//
//    //订单列表
//    @GetMapping("/list")
//    public Response<List<OrderDto>> list(@RequestParam("openid")String openid,
//                                       @RequestParam(value = "page", defaultValue = "0")Integer page,
//                                       @RequestParam(value = "size", defaultValue = "10")Integer size){
//        if (StringUtils.isEmpty(openid)) {
//            log.error("[查询订单]参数传递错误");
//            throw new AppException(ResponseEnum.PARAM_ERROR);
//        }
//
//        return Response.success(null);
//    }
//
//    //订单详情
//    @GetMapping("/detail")
//    public Response<List<OrderDto>> detail(@RequestParam("orderId") String orderId,
//                                           @RequestParam("openid")String openid,
//                                           @RequestParam(value = "page", defaultValue = "0")Integer page,
//                                           @RequestParam(value = "size", defaultValue = "10")Integer size){
//
//        return Response.success(null);
//    }
//
//    //取消订单
//    @GetMapping("/cancel")
//    public Response<List<OrderDto>> detail(@RequestParam("orderId") String orderId,
//                                           @RequestParam("openid")String openid){
//        return Response.success(null);
//    }


}
