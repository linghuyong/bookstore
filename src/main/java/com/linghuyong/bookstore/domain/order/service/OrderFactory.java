package com.linghuyong.bookstore.domain.order.service;


import com.linghuyong.bookstore.domain.order.entity.Order;
import com.linghuyong.bookstore.domain.order.repository.po.OrderPO;
import org.springframework.stereotype.Service;

@Service
public class OrderFactory {

    public OrderPO createOrderPO(Order order) {
        OrderPO orderPO = new OrderPO();
        orderPO.setId(order.getId());
        orderPO.setBookId(order.getBookId());
        orderPO.setUserId(order.getUserId());
        orderPO.setPrice(order.getPrice());
        orderPO.setStatus(order.getStatus());
        orderPO.setCreatedTime(order.getCreatedTime());
        orderPO.setUpdatedTime(order.getUpdatedTime());

        return orderPO;
    }

    public Order createOrder(OrderPO orderPO) {
        Order order = new Order();
        order.setId(orderPO.getId());
        order.setBookId(orderPO.getBookId());
        order.setUserId(orderPO.getUserId());
        order.setPrice(orderPO.getPrice());
        order.setStatus(orderPO.getStatus());
        order.setCreatedTime(orderPO.getCreatedTime());
        order.setUpdatedTime(orderPO.getUpdatedTime());
        return order;
    }
}
