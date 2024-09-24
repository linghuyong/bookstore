package com.linghuyong.bookstore.domain.user.entity;

import lombok.Data;

/**
 * Book实体，设计为聚合根
 */
@Data
public class User {
    // id
    Long id;

    // 用户名
    String username;

    // 电子邮箱
    String email;

    // 账号启用
    boolean enable;
}
