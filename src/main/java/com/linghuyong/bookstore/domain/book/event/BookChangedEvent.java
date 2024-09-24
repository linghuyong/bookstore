package com.linghuyong.bookstore.domain.book.event;

import org.springframework.context.ApplicationEvent;

public class BookChangedEvent extends ApplicationEvent {
    public BookChangedEvent(Object source) {
        super(source);
    }
}
