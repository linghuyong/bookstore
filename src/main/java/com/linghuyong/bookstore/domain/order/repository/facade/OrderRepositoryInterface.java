package com.linghuyong.bookstore.domain.order.repository.facade;


import com.linghuyong.bookstore.domain.order.repository.po.OrderPO;

public interface OrderRepositoryInterface {

    OrderPO insertOrder(OrderPO orderPO);

    // 更新使用乐观锁的机制
    OrderPO updateOrder(OrderPO orderPO, long lastUpdatedTime);

    OrderPO getOrder(long orderId);
}