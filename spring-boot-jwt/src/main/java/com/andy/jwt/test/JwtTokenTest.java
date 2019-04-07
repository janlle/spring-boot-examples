package com.andy.jwt.test;

import com.andy.jwt.util.JwtTokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-08-27
 **/
public class JwtTokenTest {

    public static void main(String[] args) throws Exception {
        Map<String, String> body = new HashMap<>();
        body.put("name", "张三");
        body.put("age", "12");
        body.put("sex", "男");
        String token = JwtTokenUtil.createToken(body);
        System.out.println(token);
        Map<String, String> result = JwtTokenUtil.verifyToken(token);
        System.out.println(result);
    }


}
