package com.linghuyong.bookstore.interfaces.dto;

import lombok.Data;

// 订单DTO
@Data
public class OrderDTO {
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
}
