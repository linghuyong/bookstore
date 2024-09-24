package com.linghuyong.bookstore.domain.user.service;

import com.linghuyong.bookstore.domain.user.entity.User;
import com.linghuyong.bookstore.domain.user.repository.po.UserPO;
import org.springframework.stereotype.Service;

@Service
public class UserFactory {

    public UserPO createUserPO(User user, String password, String salt) {
        UserPO userPO = new UserPO();
        userPO.setId(user.getId());
        userPO.setUsername(user.getUsername());
        userPO.setEmail(user.getEmail());
        userPO.setPassword(password);
        userPO.setSalt(salt);
        userPO.setEnable(user.isEnable());
        return userPO;
    }

    public User createUser(UserPO userPO) {
        User user = new User();
        user.setId(userPO.getId());
        user.setUsername(userPO.getUsername());
        user.setEmail(userPO.getEmail());
        user.setEnable(userPO.isEnable());
        return user;
    }
}
