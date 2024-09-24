package com.linghuyong.bookstore.domain.order.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("OrderT")
public class OrderPO {
    @TableId(value = "id", type = IdType.AUTO)
    // id
    Long id;

    // 图书id
    Long bookId;

    // 下单者的用户id
    Long userId;

    // 订单价格
    Double price;

    // 订单状态
    Integer status;

    // 创建订单的时间戳
    Long createdTime;

    // 订单变更时间戳，同时也做乐观锁用
    Long updatedTime;
}
