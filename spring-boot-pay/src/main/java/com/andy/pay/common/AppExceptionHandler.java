package com.andy.pay.common;

import com.andy.pay.common.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.FailedLoginException;
import javax.security.sasl.AuthenticationException;
import java.util.Locale;

/**
 * 全局异常处理器
 *
 * @author: Mr.lyon
 * @createBy: 2018-05-05 22:08
 **/
@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

    private String url = "http://localhost:8888/";

    //拦截登录异常
    @ExceptionHandler(value = AppException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:".concat(url).concat("/login"));
    }

    @Autowired
    private ApplicationContext applicationContext;


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApplicationException.class)
    public Result handleBaseException(ApplicationException e) {
        String message = applicationContext.getMessage("", null, Locale.getDefault());
        return Result.build(41005, message, null);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public Result handleAuthenticationException(Throwable e) {
        String message = applicationContext.getMessage("", null, Locale.getDefault());
        return Result.build(41005, message, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FailedLoginException.class)
    public Result handleFailedLoginException(FailedLoginException e) {
        String message = applicationContext.getMessage("", null, Locale.getDefault());
        return Result.build(41004, message, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleBaseException(IllegalArgumentException e) {
        String message = e.getMessage();
        if (StringUtils.isEmpty(message)) {
            message = "参数错误!";
        }
        return Result.build(41003, message, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleBaseException(MissingServletRequestParameterException e) {
        String message = e.getMessage();
        if (StringUtils.isEmpty(message)) {
            message = "缺少请求参数!";
        }
        return Result.build(41002, message, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleBaseException(MethodArgumentNotValidException e) {
        String message = e.getMessage();
        if (StringUtils.isEmpty(message)) {
            message = "请求参数错误!";
        }
        return Result.build(41001, message, null);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public Result handleBaseException(Throwable e) {
        log.warn("", e);
        String message = e.getMessage();
        if (StringUtils.isEmpty(message)) {
            message = "服务器内部严重错误!";
        }
        return Result.build(59999, message, null);
    }

}
