package com.MarvelAPI.util;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component("md5Encoder")
public class MD5Encode implements PasswordEncoder{

    public String encode(CharSequence password) {
        return DigestUtils.md5DigestAsHex(password.toString().getBytes());
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(this.encode(rawPassword));
    }
}
