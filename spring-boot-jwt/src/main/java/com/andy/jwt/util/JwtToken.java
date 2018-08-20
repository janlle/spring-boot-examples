package com.andy.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Leone
 * @since: 2018-04-16 09:28
 **/
@Slf4j
public class JwtToken {
    //公用秘钥保存在服务器中，客户端无法知道
    private static final String SECRET = "andy";

    private static final String ISSUER = "jwt-user";

    //生成token
    public static String createToken() throws Exception {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 1);
        Date expirsDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("name", "james")
                .withClaim("age", 23)
                .withClaim("org", "今日头条")
                .withExpiresAt(expirsDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    public static Map<String, Claim> verifToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("登录凭证验证失败，请重新登录");
        }
        return jwt.getClaims();
    }

    //---------------------------------------------------------------------------------------------

    public static String getToken(Map<String, String> claims) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTCreator.Builder builder = JWT.create().withIssuer(ISSUER).withExpiresAt(DateUtils.addDays(new Date(), 1));
            claims.forEach((k, v) -> builder.withClaim(k, v));
            return builder.sign(algorithm);
        } catch (Exception e) {
            log.info("create jwt token is fail！");
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Map<String, String> verifyToken(String token) {
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256(SECRET);
        } catch (Exception e) {
            log.info("verify jwt token is fail！");
            throw new RuntimeException(e.getMessage());
        }
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> map = jwt.getClaims();
        Map<String, String> resultMap = Maps.newHashMap();
        map.forEach((k, v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }

}
