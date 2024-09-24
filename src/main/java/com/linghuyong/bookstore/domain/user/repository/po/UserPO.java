package com.linghuyong.bookstore.domain.user.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("User")
public class UserPO {
    @TableId(value = "id", type = IdType.AUTO)
    // id
    Long id;

    // 用户名
    String username;

    // 电子邮箱
    String email;

    // 密码
    String password;

    // 保存密码时，加密用的盐
    String salt;

    // 账号启用
    boolean enable;
}
