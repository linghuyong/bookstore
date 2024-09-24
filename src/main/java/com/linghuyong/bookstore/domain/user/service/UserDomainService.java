package com.linghuyong.bookstore.domain.user.service;

import com.linghuyong.bookstore.domain.security.entity.LoginUser;
import com.linghuyong.bookstore.domain.security.service.SecurityDomainService;
import com.linghuyong.bookstore.domain.user.entity.User;
import com.linghuyong.bookstore.domain.user.repository.facade.UserRepositoryInterface;
import com.linghuyong.bookstore.domain.user.repository.po.UserPO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserDomainService {

    @Resource(name= "userRepositoryImpl")
    private UserRepositoryInterface userRepo;
    @Resource
    private UserFactory userFactory;
    @Resource
    private UserCacheService userCacheService;

    @Resource
    private SecurityDomainService securityDomainService;

    // 用户注册
    public User registerUser(User user, String password) throws Exception {
        LoginUser encryptedPassword = securityDomainService.calcEncryptedPassword(user.getUsername(), password);
        UserPO userPO = userFactory.createUserPO(user, encryptedPassword.getPassword(), encryptedPassword.getSalt());
        userPO.setEnable(true);
        userPO = userRepo.saveUser(userPO);
        return userFactory.createUser(userPO);
    }

    public User getUser(long userId) {
        return userCacheService.getUser(userId);
    }
}
