package com.linghuyong.bookstore.domain.user.service;

import com.linghuyong.bookstore.domain.user.entity.User;
import com.linghuyong.bookstore.domain.user.repository.facade.UserRepositoryInterface;
import com.linghuyong.bookstore.domain.user.repository.po.UserPO;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames="book")
@Service
public class UserCacheService {

    @Resource(name= "userRepositoryImpl")
    private UserRepositoryInterface userRepo;

    @Resource
    private UserFactory userFactory;

    @Cacheable
    public User getUser(long userId) {
        UserPO userPO = userRepo.getUserById(userId);
        if (userPO == null) {
            return null;
        }
        return userFactory.createUser(userPO);
    }
}
