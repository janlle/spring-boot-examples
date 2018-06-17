package com.andy.pay.common;

import com.andy.pay.common.exception.AppException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AppExceptionHandler {

    private String url = "http://localhost:8888/";

    //拦截登录异常
    @ExceptionHandler(value = AppException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:".concat(url).concat("/login"));
    }


}
