package com.linghuyong.bookstore.interfaces.assembler;

import com.linghuyong.bookstore.domain.order.entity.Order;
import com.linghuyong.bookstore.interfaces.dto.OrderDTO;

public class OrderAssembler {
    public static OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setBookId(order.getBookId());
        dto.setUserId(order.getUserId());
        dto.setPrice(order.getPrice());
        dto.setStatus(order.getStatus());
        dto.setCreatedTime(order.getCreatedTime());

        return dto;
    }
}
