package com.linghuyong.bookstore.domain.order.repository.persistence;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.linghuyong.bookstore.domain.order.repository.facade.OrderRepositoryInterface;
import com.linghuyong.bookstore.domain.order.repository.mapper.OrderDao;
import com.linghuyong.bookstore.domain.order.repository.po.OrderPO;
import com.linghuyong.bookstore.infrastructure.common.exception.ServerRuntimeException;
import com.linghuyong.bookstore.interfaces.dto.ErrorCode;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 * Book的Mybtis的Repo实现
 */
@Repository
public class OrderRepositoryImpl implements OrderRepositoryInterface {
    @Resource
    private OrderDao orderDao;

    @Override
    public OrderPO insertOrder(OrderPO orderPO) {
        orderDao.insert(orderPO);
        return orderPO;
    }

    @Override
    public OrderPO updateOrder(OrderPO orderPO, long lastUpdatedTime) {
        UpdateWrapper<OrderPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", orderPO.getId())
                .eq("updated_time", lastUpdatedTime);
        int rows = orderDao.update(orderPO, updateWrapper);
        if (rows == 0) {
            throw new ServerRuntimeException(ErrorCode.RetryLater, "order is updating");
        }
        return orderPO;
    }

    @Override
    public OrderPO getOrder(long orderId) {
        return orderDao.selectById(orderId);
    }
}
