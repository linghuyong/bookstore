package com.linghuyong.bookstore.application.service;

import com.linghuyong.bookstore.domain.book.entity.Book;
import com.linghuyong.bookstore.domain.book.service.BookDomainService;
import com.linghuyong.bookstore.domain.page.service.PageDomainService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageApplicationService {
    @Resource
    private PageDomainService pageDomainService;

    @Resource
    private BookDomainService bookDomainService;

    public List<Book> getRecommend(long userId) {
        List<Long> bookIds = pageDomainService.getRecommend(userId);

        List<Book> books = new ArrayList<>();
        // 此处可以优化成批量的
        for (Long id: bookIds) {
            books.add(bookDomainService.getBook(id));
        }
        return books;
    }
}
