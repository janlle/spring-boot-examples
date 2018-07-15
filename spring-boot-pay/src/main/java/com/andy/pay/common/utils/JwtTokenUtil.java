package com.andy.pay.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-05-8
 **/
@Slf4j
public class JwtTokenUtil {

    //公用秘钥
    private static final String SECRET = "ygoth-ygoworld-reg";

    private static final String ISSUER = "jwt-token";

    public static String getToken(Map<String, String> claims) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 7);
            JWTCreator.Builder builder = JWT.create().withIssuer(ISSUER).withExpiresAt(calendar.getTime());
            claims.forEach((k, v) -> builder.withClaim(k, v));
            return builder.sign(algorithm);
        } catch (Exception e) {
            log.info("token [创建失败]->{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Map<String, String> verifyToken(String token) {
        Algorithm algorithm;
        try {
            algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> map = jwt.getClaims();
            Map<String, String> resultMap = new HashMap<>();
            map.forEach((k, v) -> resultMap.put(k, v.asString()));
            return resultMap;
        } catch (Exception e) {
            log.info("token [校验失败]->{}", e.getMessage());
        }
        return null;
    }

}
