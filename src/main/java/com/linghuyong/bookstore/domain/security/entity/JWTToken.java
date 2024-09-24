package com.linghuyong.bookstore.domain.security.entity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;

import java.util.Date;

// 简单的JWT，仅使用userId作为payload检验
@Data
public class JWTToken {
    private static final String SECRET = "!@#$%^&*()nwoijl..wei";

    // jwt的token值
    private String token;

    // 过期时间的timestamp
    private long expireAt;

    // 用户id
    private long userId;

    private void JWToken() {
    }

    public static JWTToken create(long id){
        // 1天后过期
        long delay = 60 * 1000 * 60 * 24;
        JWTToken token = new JWTToken();
        token.userId = id;
        token.expireAt = System.currentTimeMillis() + delay;
        token.token = JWT.create()
                .withClaim("userId",id)
                .withExpiresAt(new Date(token.expireAt))
                .sign(Algorithm.HMAC256(SECRET));

        return token;
    }

    public static JWTToken create(String token){
        JWTToken jwtToken = new JWTToken();
        jwtToken.token = token;

        return jwtToken;
    }

    public boolean isValid() {
        try {
            DecodedJWT verify = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(this.token);
            this.userId = verify.getClaim("userId").asLong();
            this.expireAt = verify.getExpiresAt().getTime();
        } catch (Exception exception) {
            return false;
        }
        return expireAt >= System.currentTimeMillis();
    }
}
