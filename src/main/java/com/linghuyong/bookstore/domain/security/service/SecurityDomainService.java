package com.linghuyong.bookstore.domain.security.service;

import com.linghuyong.bookstore.domain.security.entity.JWTToken;
import com.linghuyong.bookstore.domain.security.entity.LoginUser;
import com.linghuyong.bookstore.domain.security.entity.valueobject.Authority;
import jakarta.annotation.Resource;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "jwt_token")
public class SecurityDomainService {

    @Resource
    private AuthenticationConfiguration authenticationConfiguration;

    @Resource
    private CacheManager cacheManager;

    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    // 用户注册时，加密用户密码
    public LoginUser calcEncryptedPassword(String userName, String password) throws Exception {
        if (password.length() < 8) {
            throw new Exception("password at least 8 bits.");
        }

        return LoginUser.create(userName, passwordEncoder.encode(password));
    }

    public JWTToken authenticate(String username, String password) throws Exception {
        Authentication authentication = authenticationConfiguration.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password));

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        JWTToken token = JWTToken.create(loginUser.getUserId());

        // 缓存token，在cache中就表示token没有过期，value不重要
        String key = getCacheKey(token.getToken());
        this.getCache().put(key, new SimpleValueWrapper(null));

        return token;
    }

    private Cache getCache() {
        return cacheManager.getCache("jwt_token");
    }

    private String getCacheKey(String token) {
        return String.format("jwt:%s", token);
    }

    // 解析并验证token
    public LoginUser parseJWTToken(String token) {
        String key = getCacheKey(token);
        Cache.ValueWrapper valueWrapper = this.getCache().get(key);
        if (valueWrapper == null) {
            return null;
        }
        JWTToken jwtToken = JWTToken.create(token);
        if (!jwtToken.isValid()) {
            return null;
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(jwtToken.getUserId());
        loginUser.setAuthority(Authority.User);
        return loginUser;
    }
}
