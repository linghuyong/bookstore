package com.linghuyong.bookstore.application.service;

import com.linghuyong.bookstore.domain.book.entity.Book;
import com.linghuyong.bookstore.domain.book.service.BookDomainService;
import com.linghuyong.bookstore.domain.order.entity.Order;
import com.linghuyong.bookstore.domain.order.service.OrderDomainService;
import com.linghuyong.bookstore.infrastructure.common.exception.ServerRuntimeException;
import com.linghuyong.bookstore.interfaces.dto.ErrorCode;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderApplicationService {

    @Resource
    private OrderDomainService orderDomainService;

    @Resource
    private BookDomainService bookDomainService;


    @Transactional
    public Order createOrder(long bookId, long userId) {
        // 库存-1
        Book book = bookDomainService.changeBookStock(bookId, -1);
        return orderDomainService.createOrder(bookId, userId, getOrderPrice(book));
    }

    // 此处可以扩展，由独立的营销业务领域计算订单价格
    private double getOrderPrice(Book book) {
        return book.getPrice();
    }

    public Order getOrder(long orderId, long userId) {
        Order order = orderDomainService.getOrder(orderId);
        if (order == null || order.getUserId() != userId) {
            throw new ServerRuntimeException(ErrorCode.ResourceNotFound, "order does not exist");
        }
        return order;
    }


    @Transactional
    public Order cancelOrder(long orderId, long userId) {
        Order order = orderDomainService.cancelOrder(orderId, userId);
        // 库存+1
        bookDomainService.changeBookStock(order.getBookId(), 1);
        return order;
    }

    public Order acceptOrder(long orderId) {
        Order order = orderDomainService.acceptOrder(orderId);
        // 库存+1
        bookDomainService.changeBookStock(order.getBookId(), 1);
        return order;
    }

    public Order finishOrder(long orderId) {
        Order order = orderDomainService.finishOrder(orderId);
        // 库存+1
        bookDomainService.changeBookStock(order.getBookId(), 1);
        return order;
    }
}