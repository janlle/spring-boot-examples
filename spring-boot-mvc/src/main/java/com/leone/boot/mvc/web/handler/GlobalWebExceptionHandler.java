package com.leone.boot.mvc.web.handler;

import com.google.common.collect.Maps;
import com.leone.boot.common.exception.BizException;
import com.leone.boot.common.exception.SystemException;
import com.leone.boot.mvc.web.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

import static com.leone.boot.common.response.ResponseCode.SYSTEM_ERROR;


/**
 * 全局异常处理器
 *
 * @author leone
 */
@Slf4j
//@ControllerAdvice
public class GlobalWebExceptionHandler {

    /**
     * 自定义方法参数校验异常处理器
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException occurred.", ex);
        Map<String, String> errors = Maps.newHashMapWithExpectedSize(1);
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    /**
     * 自定义业务异常处理器
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result<String> exceptionHandler(BizException bizException) {
        log.error("bizException occurred.", bizException);
        Result<String> result = new Result<>();
        result.setCode(bizException.getErrorCode().getCode());
        result.setMessage(bizException.getErrorCode().getMessage());
        result.setSuccess(false);
        return result;
    }

    /**
     * 自定义系统异常处理器
     */
    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result<String> systemExceptionHandler(SystemException systemException) {
        log.error("systemException occurred.", systemException);
        Result<String> result = new Result<>();
        result.setCode(systemException.getErrorCode().getCode());
        result.setMessage(systemException.getErrorCode().getMessage());
        result.setSuccess(false);
        return result;
    }

    /**
     * 自定义系统异常处理器
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result<String> throwableHandler(Throwable throwable) {
        log.error("throwable occurred.", throwable);
        Result<String> result = new Result<>();
        result.setCode(SYSTEM_ERROR.name());
        result.setMessage("哎呀，当前网络比较拥挤，请您稍后再试~");
        result.setSuccess(false);
        return result;
    }


}
