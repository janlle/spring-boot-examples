package com.andy.security.config;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Desc: 密码的加密和匹配
 * @author: Mr.ruoLin
 * @createBy: 2018-04-21 14:46
 **/
public class AppPasswordEncoder implements PasswordEncoder {

    private static final String SALT ="abcd";

    private final Md5PasswordEncoder encoder = new Md5PasswordEncoder();

    @Override
    public String encode(CharSequence charSequence) {
        return encoder.encodePassword(charSequence.toString(), SALT);
    }

    @Override
    public boolean matches(CharSequence charSequence, String encoderPassword) {
        return encoder.isPasswordValid(charSequence.toString(), encoderPassword, SALT);
    }
}
