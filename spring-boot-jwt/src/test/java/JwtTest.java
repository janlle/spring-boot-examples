import com.leone.boot.jwt.util.JwtTokenUtil;

import java.util.Map;

/**
 * @author leone
 * @since 2018-04-16
 **/
public class JwtTest {

    public static void main(String[] args) throws Exception {

        String token = JwtTokenUtil.createToken(null);
        String tokenExpire = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJvcmciOiLku4rml6XlpLTmnaEiLCJuYW1lIjoiamFtZXMiLCJleHAiOjE1MjM4NDM1NDksImFnZSI6MjN9.CS-PXcfpmjLErjNVbfIYlmEDcYhz4pyZ7QimTD2Xcg0";

        System.out.println("Token="+ token);
        Map<String, String> result = JwtTokenUtil.verifyToken(token);
        System.out.println(result);

    }


}
