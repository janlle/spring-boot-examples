package com.andy.pay.web.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Api(tags = "用户api接口文档")
@RequestMapping(value = "/api/user")
public class UserController {

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private TokenService tokenService;
//
//    @Autowired
//    private ShiroProperty shiroProperty;
//
//    private String emailReg = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
//    private String phoneReg = "^[1][3456789]\\d{9}$";
//
//    @ApiOperation(value = "测试", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @GetMapping("/")
//    public String home() {
//        return "success";
//    }
//
//    @ApiOperation(value = "用户登录接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @GetMapping("/login")
//    public Result<Object> login(String account, String password, HttpServletRequest request, HttpServletResponse response) {
//        tokenService.login(RandomUtil.getNum(4));
//        return Result.build(ResultEnum.SUCCESS, null);
//    }
//
//    @ApiOperation(value = "用户退出接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @GetMapping("/logout")
//    public Result<String> logout(Integer userId) {
//        log.info("logout");
//        tokenService.logout(userId.toString());
//        log.info("logout [删除用户token成功]->{}", userId);
//        return Result.success(20000);
//    }
//
//    @ApiOperation(value = "用户注册接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @PostMapping("/register")
//    public Result<String> register(@RequestBody User user) {
//        return Result.error(50000);
//    }
//
//    @ApiOperation(value = "根据用户id获取用户信息", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @GetMapping("/{userId}")
//    public Result<Object> user(@PathVariable("userId") String userId) {
//        return Result.success(20000);
//    }
//
//    @ApiOperation(value = "用户认证授权接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @GetMapping("/authentication")
//    public Result<String> verifyToken(String token, Integer userId, HttpServletRequest request, HttpServletResponse response) {
//
//        return Result.error(50000);
//    }
//
//    @ApiOperation(value = "测试", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @GetMapping("/property")
//    public Result<Object> shiroProperty() {
//
//        ShiroProperty shiro = new ShiroProperty();
//
//        shiro.setAnonUrls(shiroProperty.getAnonUrls());
//        shiro.setCacheDays(shiroProperty.getCacheDays());
//        shiro.setCoreUrls(shiroProperty.getCoreUrls());
//        shiro.setMultiLogin(shiroProperty.isMultiLogin());
//        shiro.setRedisPrefix(shiroProperty.getRedisPrefix());
//        shiro.setTokenPrefix(shiroProperty.getTokenPrefix());
//        shiro.setAnonUrls(shiroProperty.getAnonUrls());
//        shiro.setAuthUrls(shiroProperty.getAuthUrls());
//        return Result.build(20000, "SUCCESS", shiro);
//    }

}
