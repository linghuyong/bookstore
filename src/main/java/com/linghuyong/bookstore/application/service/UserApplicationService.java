package com.linghuyong.bookstore.application.service;

import com.linghuyong.bookstore.domain.security.entity.JWTToken;
import com.linghuyong.bookstore.domain.security.service.SecurityDomainService;
import com.linghuyong.bookstore.domain.user.entity.User;
import com.linghuyong.bookstore.domain.user.service.UserDomainService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

    @Resource
    private UserDomainService userDomainService;

    @Resource
    private SecurityDomainService securityDomainService;


    public User registerUser(User user, String password) throws Exception {
        return userDomainService.registerUser(user, password);
    }

    public JWTToken login(String username, String password) throws Exception {
        return securityDomainService.authenticate(username, password);
    }

    public User getUser(long userId) {
        return userDomainService.getUser(userId);
    }
}