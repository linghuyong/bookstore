package com.linghuyong.bookstore.domain.book.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("Book")
public class BookPO {
    @TableId(value = "id", type = IdType.AUTO)
    // id
    Long id;

    // 标题
    String title;

    // 描述
    String description;

    // 图书分类
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
