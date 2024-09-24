package com.linghuyong.bookstore.domain.order.entity;

import com.linghuyong.bookstore.domain.order.entity.valueobject.OrderStatus;
import com.linghuyong.bookstore.infrastructure.common.exception.ServerRuntimeException;
import com.linghuyong.bookstore.interfaces.dto.ErrorCode;
import lombok.Data;

import java.util.Arrays;

/**
 * Book实体，设计为聚合根
 */
@Data
public class Order {
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

    // 上次订单的时间戳
    Long updatedTime;

    public static Order create(long bookId, long userId, double price) {
        Order order = new Order();
        order.bookId = bookId;
        order.userId = userId;
        order.price = price;
        order.status = OrderStatus.CREATED.ordinal();
        order.createdTime = System.currentTimeMillis();
        order.updatedTime = System.currentTimeMillis();

        return order;
    }

    public void cancel() {
        if (!Arrays.asList(OrderStatus.CREATED.ordinal(), OrderStatus.PENDING.ordinal()).contains(this.status)) {
            throw new ServerRuntimeException(ErrorCode.ParameterError, "order is in valid status");
        }

        this.status = OrderStatus.CANCELED.ordinal();
        this.updatedTime = System.currentTimeMillis();
    }

    public void finish() {
        if (this.status != OrderStatus.PENDING.ordinal()) {
            throw new ServerRuntimeException(ErrorCode.ParameterError, "order is in valid status");
        }

        this.status = OrderStatus.FINISHED.ordinal();
        this.updatedTime = System.currentTimeMillis();
    }

    // 系统接受订单
    public void accept() {
        if (this.status != OrderStatus.CREATED.ordinal()) {
            throw new ServerRuntimeException(ErrorCode.ParameterError, "order is in valid status");
        }

        this.status = OrderStatus.FINISHED.ordinal();
        this.updatedTime = System.currentTimeMillis();
    }
}
