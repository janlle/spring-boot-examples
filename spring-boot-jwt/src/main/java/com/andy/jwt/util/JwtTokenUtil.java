package com.andy.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Leone
 * @since 2018-04-16
 **/
@Slf4j
public class JwtTokenUtil {

    //公用秘钥保存在服务器中，客户端无法知道
    private static final String SECRET = "#ili^0+%98@";

    private static final String ISSUER = "jwt-user";

    /**
     * 生成token
     *
     * @param claims
     * @return
     */
    public static String createToken(Map<String, String> claims) {
        try {
            Map<String, Object> header = new HashMap<>();
            header.put("alg", "HS256");
            header.put("typ", "JWT");

            Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.MINUTE, 3);
            Date expireDate = nowTime.getTime();

            JWTCreator.Builder builder = JWT.create()
                    .withHeader(header)
                    .withIssuer(ISSUER)
                    .withExpiresAt(expireDate)
                    .withIssuedAt(nowTime.getTime())
                    .withSubject("user");
            claims.forEach(builder::withClaim);
            return builder.sign(Algorithm.HMAC256(SECRET));
        } catch (Exception e) {
            log.info("create jwt token failed message {}", e.getMessage());
            return null;
        }
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public static Map<String, String> verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).withIssuer(ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, String> result = Maps.newHashMap();
            jwt.getClaims().forEach((k, v) -> result.put(k, v.asString()));
            return result;
        } catch (Exception e) {
            log.info("verify jwt token failed message:{}", e.getMessage());
            return null;
        }
    }

}
