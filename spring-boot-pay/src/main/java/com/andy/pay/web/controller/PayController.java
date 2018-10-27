package com.andy.pay.web.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Api(tags = "支付回调接口")
@RequestMapping(value = "/api/pay/notify")
public class PayController {

//    @Autowired
//    private UserService userService;
//
//    @ApiOperation(value = "微信回调接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @GetMapping("/weChatNotify")
//    public Result<String> weChatNotify() {
//        return Result.success(20000);
//    }
//
//    @ApiOperation(value = "生成二维码", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @GetMapping("/code")
//    public void code(@RequestParam(required = false, defaultValue = "http://www.baidu.com") String content, HttpServletRequest request, HttpServletResponse response) {
//        QRCodeUtil.createQRCode(content, response);
//    }

}
