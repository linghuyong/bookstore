package com.linghuyong.bookstore.domain.order.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linghuyong.bookstore.domain.order.repository.po.OrderPO;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends BaseMapper<OrderPO> {

}
