package com.linghuyong.bookstore.domain.order.service;

import com.linghuyong.bookstore.domain.order.entity.Order;
import com.linghuyong.bookstore.domain.order.event.OrderChangedEvent;
import com.linghuyong.bookstore.domain.order.repository.facade.OrderRepositoryInterface;
import com.linghuyong.bookstore.domain.order.repository.po.OrderPO;
import jakarta.annotation.Resource;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames="order")
@Service
public class OrderCacheService {

    @Resource(name= "orderRepositoryImpl")
    private OrderRepositoryInterface orderRepo;

    @Resource
    private OrderFactory orderFactory;

    @Resource
    private CacheManager cacheManager;

    private Cache getOrderCache() {
        return cacheManager.getCache("order");
    }

    @Cacheable("order")
    public Order getOrder(long orderId) {
        OrderPO orderPO = orderRepo.getOrder(orderId);
        if (orderPO == null) {
            return null;
        }
        return orderFactory.createOrder(orderPO);
    }

    @EventListener(OrderChangedEvent.class)
    public void onApplicationEvent(OrderChangedEvent event) {
        Order order = (Order) event.getSource();
        this.getOrderCache().evict(order.getId());
    }
}
