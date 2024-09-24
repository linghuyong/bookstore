package com.linghuyong.bookstore.domain.user.service;

import com.linghuyong.bookstore.domain.security.entity.LoginUser;
import com.linghuyong.bookstore.domain.security.entity.valueobject.Authority;
import com.linghuyong.bookstore.domain.user.repository.facade.UserRepositoryInterface;
import com.linghuyong.bookstore.domain.user.repository.po.UserPO;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSercurityService implements UserDetailsService {
    @Resource(name = "userRepositoryImpl")
    private UserRepositoryInterface userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO userPO = userRepo.getUserByUsername(username);
        if (userPO == null) {
            throw new UsernameNotFoundException(username);
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(userPO.getId());
        loginUser.setUsername(userPO.getUsername());
        loginUser.setPassword(userPO.getPassword());
        // 用户接口的登录，只授权User权限。
        loginUser.setAuthority(Authority.User);

        return loginUser;
    }
}