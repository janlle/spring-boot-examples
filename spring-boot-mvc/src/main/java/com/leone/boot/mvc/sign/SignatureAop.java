package com.leone.boot.mvc.sign;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Objects;

/**
 * <p>{
 * "appId":"109098",
 * "nonceStr":"nonceStr",
 * "timestamp":1558703431,
 * "data":{
 * "userId":1001,
 * "account":"1009",
 * "password":"1209098",
 * "description":"hello world",
 * "age":18,
 * "deleted": false
 * },
 * "sign":""
 * }
 *
 * @author leone
 * @since 2019-05-24
 **/
@Slf4j
@Aspect
@Component
public class SignatureAop {

    private ObjectMapper objectMapper;

    public SignatureAop(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Before("@annotation(com.leone.boot.mvc.sign.Signature)")
    public void before(JoinPoint point) {
        Object[] args = point.getArgs();
        if (Objects.nonNull(args) && args.length > 1) {
            try {
                if (!(args[0] instanceof SignatureBean)) {
                    throw new IllegalArgumentException("param type is not signature type");
                }
                SignatureBean signatureBean = (SignatureBean) args[0];
                signatureBean.setSecret("abc");
                String paramJson = objectMapper.writeValueAsString(signatureBean);
                Assert.notNull(signatureBean.getSign(), "sign missing");
                String result = Md5Util.MD5(paramJson);
                boolean checkTime = SignatureUtil.checkTime(signatureBean.getTimestamp());
                Assert.isTrue(checkTime, "time invalidate");
                Assert.isTrue(result.equals(signatureBean.getSign()), "sign check wrong");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
