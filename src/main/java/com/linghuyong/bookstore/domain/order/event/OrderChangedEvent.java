package com.linghuyong.bookstore.domain.order.event;

import org.springframework.context.ApplicationEvent;

public class OrderChangedEvent extends ApplicationEvent {
    public OrderChangedEvent(Object source) {
        super(source);
    }
}
