package com.linghuyong.bookstore.domain.order.service;

import com.linghuyong.bookstore.domain.order.entity.Order;
import com.linghuyong.bookstore.domain.order.event.OrderChangedEvent;
import com.linghuyong.bookstore.domain.order.event.OrderCreatedEvent;
import com.linghuyong.bookstore.domain.order.repository.persistence.OrderRepositoryImpl;
import com.linghuyong.bookstore.domain.order.repository.po.OrderPO;
import com.linghuyong.bookstore.infrastructure.common.exception.ServerRuntimeException;
import com.linghuyong.bookstore.interfaces.dto.ErrorCode;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class OrderDomainService {

    @Resource(name= "orderRepositoryImpl")
    private OrderRepositoryImpl orderRepo;
    @Resource
    private OrderFactory orderFactory;
    @Resource
    private OrderCacheService orderCacheService;

    @Resource
    private ApplicationContext context;

    public Order createOrder(long bookId, long userId, double price) {
        Order order = Order.create(bookId, userId, price);
        OrderPO ret = orderRepo.insertOrder(orderFactory.createOrderPO(order));
        order = orderFactory.createOrder(ret);
        context.publishEvent(new OrderCreatedEvent(order));
        return order;
    }

    public Order getOrder(long orderId) {
        return orderCacheService.getOrder(orderId);
    }

    public Order cancelOrder(long orderId, long userId) {
        Order order = orderCacheService.getOrder(orderId);
        if (order == null || order.getUserId() != userId) {
            throw new ServerRuntimeException(ErrorCode.ResourceNotFound, "order does not exist");
        }
        long lastUpdatedTime = order.getUpdatedTime();
        order.cancel();
        orderRepo.updateOrder(orderFactory.createOrderPO(order), lastUpdatedTime);
        context.publishEvent(new OrderChangedEvent(order));
        return order;
    }

    public Order finishOrder(long orderId) {
        Order order = orderCacheService.getOrder(orderId);
        if (order == null) {
            throw new ServerRuntimeException(ErrorCode.ResourceNotFound, "order does not exist");
        }
        long lastUpdatedTime = order.getUpdatedTime();
        order.cancel();
        orderRepo.updateOrder(orderFactory.createOrderPO(order), lastUpdatedTime);
        context.publishEvent(new OrderChangedEvent(order));
        return order;
    }

    public Order acceptOrder(long orderId) {
        Order order = orderCacheService.getOrder(orderId);
        if (order == null) {
            throw new ServerRuntimeException(ErrorCode.ResourceNotFound, "order does not exist");
        }
        long lastUpdatedTime = order.getUpdatedTime();
        order.accept();
        orderRepo.updateOrder(orderFactory.createOrderPO(order), lastUpdatedTime);
        context.publishEvent(new OrderChangedEvent(order));
        return order;
    }
}
