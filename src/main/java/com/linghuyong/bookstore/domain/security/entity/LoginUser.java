package com.linghuyong.bookstore.domain.security.entity;

import com.linghuyong.bookstore.domain.security.entity.valueobject.Authority;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

// Security领域使用的loginUser实体
// 实现UserDetails是为了集成Spring Security
@Data
public class LoginUser implements UserDetails {
    // 用户id
    private Long userId;
    // 用户名
    private String username;

    // 加密后的密码
    private String password;

    // 随机生成的salt
    // 作废了，发现Spring Security有实现PasswordEncoder
    @Deprecated
    private String salt = Strings.EMPTY;

    // 授予的权限
    private Authority authority;

    public static LoginUser create(String username, String password) throws NoSuchAlgorithmException {
        LoginUser ret = new LoginUser();
        ret.username = username;
        ret.password = password;
        // ret.salt = genSalt();
        // ret.password = encryptPassword(password, ret.salt);
        return ret;
    }

    private static String genSalt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            // 生成一个随机字符
            char randomChar = (char) ('a' + random.nextInt(26));
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static String encryptPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        StringBuilder sb = new StringBuilder();
        for (byte b : md.digest((password + salt).getBytes(StandardCharsets.UTF_8))) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(authority);
    }
}
