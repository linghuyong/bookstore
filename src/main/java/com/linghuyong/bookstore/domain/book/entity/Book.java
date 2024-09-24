package com.linghuyong.bookstore.domain.book.entity;

import lombok.Data;

/**
 * Book实体，设计为聚合根
 */
@Data
public class Book {
    // id
    Long id;

    // 标题
    String title;

    // 描述
    String description;

    // 作者
    Author author;

    // 分类
    Integer classification;

    // 上架状态
    Boolean enable;

    // 价格
    Double price;

    // 库存
    Integer stock;

    public boolean changeStock(int delta) {
        if (stock + delta < 0) {
            return false;
        }
        this.stock += delta;
        return true;
    }
}
