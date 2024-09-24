package com.linghuyong.bookstore.domain.security.service;

import com.linghuyong.bookstore.domain.security.entity.LoginUser;
import com.linghuyong.bookstore.domain.security.entity.valueobject.Authority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

// 提供给别的领域服务，获取认证了的用户信息
@Service
public class AuthenticatedUserService {

    // 获取认证过的用户id
    public Long getAuthenticatedUserId() {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return null;
        }
        return loginUser.getUserId();
    }

    private LoginUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUser) {
            return (LoginUser) authentication.getPrincipal();
        }
        // fixe WithMockUser的补丁
        if (principal instanceof User){
            LoginUser loginUser = new LoginUser();
            loginUser.setUserId(Long.valueOf(((User)principal).getUsername()));
            loginUser.setAuthority(Authority.User);
            return loginUser;
        }

        return null;
    }
}
