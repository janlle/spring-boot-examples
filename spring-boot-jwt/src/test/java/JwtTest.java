import com.andy.jwt.util.JwtToken;
import com.auth0.jwt.interfaces.Claim;

import java.util.Map;

/**
 * @author: Leone
 * @since: 2018-04-16 09:43
 **/
public class JwtTest {

    public static void main(String[] args) throws Exception {
        String token = JwtToken.createToken();
        System.out.println("Token="+ token);

        Map<String, Claim> claims = JwtToken.verifToken(token);
        System.out.println(claims.get("name").asString());
        System.out.println(claims.get("age").asInt());
        System.out.println(claims.get("org") == null ? null : claims.get("org").asString());

        String tokenExpire = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJvcmciOiLku4rml6XlpLTmnaEiLCJuYW1lIjoiamFtZXMiLCJleHAiOjE1MjM4NDM1NDksImFnZSI6MjN9.CS-PXcfpmjLErjNVbfIYlmEDcYhz4pyZ7QimTD2Xcg0";

        Map<String, Claim> result = JwtToken.verifToken(tokenExpire);
        System.out.println(result);
    }


}
