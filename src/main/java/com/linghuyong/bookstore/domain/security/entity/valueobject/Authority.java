package com.linghuyong.bookstore.domain.security.entity.valueobject;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

// 权限对象
@Data
public class Authority implements GrantedAuthority {
    // 授予的权限字符串
    private String authority;

    // 不用外界实例化
    private Authority(String authority){
        this.authority = authority;
    }

    // 简化系统设计，就只有两类权限对象
    public static Authority Admin = new Authority("admin");
    public static Authority User = new Authority("user");
}
