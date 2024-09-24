package com.linghuyong.bookstore.domain.user.repository.persistence;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.linghuyong.bookstore.domain.user.repository.facade.UserRepositoryInterface;
import com.linghuyong.bookstore.domain.user.repository.mapper.UserDao;
import com.linghuyong.bookstore.domain.user.repository.po.UserPO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 * User的Mybatis的Repo实现
 */
@Repository
public class UserRepositoryImpl implements UserRepositoryInterface {
    @Resource
    private UserDao userDao;


    @Override
    public UserPO saveUser(UserPO authorPO) {
        userDao.insert(authorPO);
        return authorPO;
    }

    @Override
    public UserPO getUserById(long userId) {
        return userDao.selectById(userId);
    }

    @Override
    public UserPO getUserByUsername(String userName) {
        LambdaQueryWrapper<UserPO> query = new LambdaQueryWrapper<>();
        return userDao.selectOne(query.eq(true, UserPO::getUsername, userName));
    }
}
