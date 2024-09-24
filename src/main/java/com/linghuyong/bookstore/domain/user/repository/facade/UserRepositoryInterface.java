package com.linghuyong.bookstore.domain.user.repository.facade;


import com.linghuyong.bookstore.domain.user.repository.po.UserPO;

public interface UserRepositoryInterface {

    UserPO saveUser(UserPO authorPO);

    UserPO getUserById(long userId);

    UserPO getUserByUsername(String userName);
}