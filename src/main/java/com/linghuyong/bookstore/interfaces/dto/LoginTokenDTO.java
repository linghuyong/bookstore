package com.linghuyong.bookstore.interfaces.dto;

import lombok.Data;

@Data
public class LoginTokenDTO {
    // JWT token
    String token;

    // 过期时间
    Long expireTimestamp;
}
