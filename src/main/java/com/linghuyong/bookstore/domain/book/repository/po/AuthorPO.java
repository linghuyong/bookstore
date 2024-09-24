package com.linghuyong.bookstore.domain.book.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("author")
public class AuthorPO {
    @TableId(value = "id", type = IdType.AUTO)
    // id
    Long id;

    // 名字
    String name;
}
