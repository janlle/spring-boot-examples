package com.leone.boot.mvc.sign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * <p>{
 * 	"appId":"109098",
 * 	"nonceStr":"nonceStr",
 * 	"timestamp":1558703431,
 * 	"data":{
 * 		"userId":1001,
 * 		"account":"1009",
 * 		"password":"1209098",
 * 		"description":"hello world",
 * 		"age":18,
 * 		"deleted": false
 *        },
 * 	"sign":""
 * }
 *
 * @author leone
 * @since 2019-05-24
 **/
@Slf4j
@Aspect
@Component
public class SignatureAop {

    private final ObjectMapper objectMapper;

    public SignatureAop(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Before("@annotation(com.leone.boot.mvc.sign.Signature)")
    public void before(JoinPoint point) {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Object[] args = point.getArgs();
        if (Objects.nonNull(args) && args.length == 1) {
            try {
                String paramJson = objectMapper.writeValueAsString(args[0]);
                SignatureBean signatureBean = (SignatureBean) args[0];
                boolean check = SignatureUtil.check(paramJson, "");
                boolean checkTime = SignatureUtil.checkTime(signatureBean.getTimestamp());

                if ((check && checkTime)) {
                    throw new IllegalArgumentException("params check wrong");
                }

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

}
