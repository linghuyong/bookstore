package com.linghuyong.bookstore.interfaces.dto;

import lombok.Data;

// 创建资源时，id必须为null
// 修改资源时，非null的字段表示需要修改
@Data
public class UserDTO {
    // id
    private Long id;

    // 用户名
    private String username;

    // 电子邮箱
    private String email;

    // 密码
    private String password;
}
