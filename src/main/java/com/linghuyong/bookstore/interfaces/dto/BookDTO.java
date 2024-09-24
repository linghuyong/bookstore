package com.linghuyong.bookstore.interfaces.dto;

import lombok.Data;

// 创建资源时，id必须为null
// 修改资源时，非null的字段表示需要修改
@Data
public class BookDTO {
    // id
    Long id;

    // 标题
    String title;

    // 描述
    String description;

    // 图书分类, 枚举值为Classification类型
    Integer classification;

    // 作者id
    Long authorId;

    // 上架状态
    Boolean enable;

    // 价格
    Double price;

    // 库存
    Integer stock;
}
