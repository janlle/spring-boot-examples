package com.andy.shiro.config;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-21 16:51
 **/
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

    private static final long serialVersionUID = 1L;

    //验证码字符串
    private String captcha;

    public CaptchaUsernamePasswordToken(String username, char[] password,
                                        boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
