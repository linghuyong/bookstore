package com.linghuyong.bookstore.interfaces.facade;

import com.linghuyong.bookstore.application.service.OrderApplicationService;
import com.linghuyong.bookstore.domain.order.entity.Order;
import com.linghuyong.bookstore.domain.security.service.AuthenticatedUserService;
import com.linghuyong.bookstore.infrastructure.common.api.Response;
import com.linghuyong.bookstore.interfaces.assembler.OrderAssembler;
import com.linghuyong.bookstore.interfaces.dto.OrderDTO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/*
    C端接口，使用JWTToken鉴权
    User相关接口
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Resource
    private OrderApplicationService orderApplicationService;

    @Resource
    private AuthenticatedUserService authenticatedUserService;

    // 创建订单接口
    @PostMapping()
    public Response<OrderDTO> createOrder(@RequestBody Long bookId) throws Exception {
        Order order = orderApplicationService.createOrder(bookId, authenticatedUserService.getAuthenticatedUserId());
        return Response.ok(OrderAssembler.toDTO(order));
    }

    // 获取订单接口
    @GetMapping("/{orderId}")
    public Response<OrderDTO> getOrder(@PathVariable long orderId) throws Exception {
        Order order = orderApplicationService.getOrder(orderId, authenticatedUserService.getAuthenticatedUserId());
        return Response.ok(OrderAssembler.toDTO(order));
    }

    // 取消订单接口
    @PostMapping("/{orderId}/cancel")
    public Response<OrderDTO> cancelOrder(@PathVariable long orderId) {
        Order order = orderApplicationService.cancelOrder(orderId, authenticatedUserService.getAuthenticatedUserId());
        return Response.ok(OrderAssembler.toDTO(order));
    }
}
