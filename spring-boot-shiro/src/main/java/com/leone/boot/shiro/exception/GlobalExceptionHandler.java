package com.leone.boot.shiro.exception;

import com.leone.boot.shiro.common.ExceptionMessage;
import com.leone.boot.shiro.common.Result;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author leone
 **/
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidateException.class)
    public Result handleBaseException(ValidateException e) {
        logger.error("{}", e.getMessage());
        return Result.build(e.getMessage(), e.getCode(), null);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorizationException.class)
    public Result handleAuthorizationException(Throwable e) {
        logger.error("{}", e.getMessage());
        return Result.build(ExceptionMessage.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public Result handleAuthenticationException(Throwable e) {
        logger.error("{}", e.getMessage());
        return Result.build(ExceptionMessage.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleBaseException(IllegalArgumentException e) {
        logger.error("{}", e.getMessage());
        return Result.build(ExceptionMessage.BAD_REQUEST);
    }

}
