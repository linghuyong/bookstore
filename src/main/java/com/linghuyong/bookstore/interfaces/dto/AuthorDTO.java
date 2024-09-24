package com.linghuyong.bookstore.interfaces.dto;

import lombok.Data;

// 创建资源时，id必须为null
// 修改资源时，非null的字段表示需要修改
@Data
public class AuthorDTO {
    // 作者id，修改时候需要传入
    private Long id;
    // 作者name
    private String name;
}
