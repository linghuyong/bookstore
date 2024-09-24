package com.linghuyong.bookstore.interfaces.dto;

/*
    订单状态枚举值，为了简化系统，不含盖支付模块的逻辑
 */
public enum OrderStatus {
    // 刚创建的
    CREATED,
    // 待后台处理的
    PENDING,
    // 完成的
    FINISHED,
    // 取消的
    CANCELED,
}
