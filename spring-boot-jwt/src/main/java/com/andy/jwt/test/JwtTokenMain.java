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
public class JwtTokenMain {

    public static void main(String[] args) throws Exception {
        Map<String, String> body = new HashMap<>();
        body.put("name", "张三");
        body.put("age", "12");
        body.put("sex", "男");
        String token = JwtTokenUtil.createToken(body);
        System.out.println(token);

        JwtTokenUtil.verifyToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyIiwic2V4Ijoi55S3IiwiaXNzIjoiand0LXVzZXIiLCJuYW1lIjoi5byg5LiJIiwiZXhwIjoxNTM5NzYwODk2LCJpYXQiOjE1Mzk3NjA4OTYsImFnZSI6IjEyIn0.RoeEc-No1PtVp4n7o0pyCXX7rwo8sLobytBXvvpbV1o");

    }


}
