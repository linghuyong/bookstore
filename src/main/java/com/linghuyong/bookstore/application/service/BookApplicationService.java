package com.linghuyong.bookstore.application.service;

import com.linghuyong.bookstore.domain.book.entity.Author;
import com.linghuyong.bookstore.domain.book.entity.Book;
import com.linghuyong.bookstore.domain.book.service.BookDomainService;
import com.linghuyong.bookstore.infrastructure.common.exception.ServerRuntimeException;
import com.linghuyong.bookstore.interfaces.dto.ErrorCode;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class BookApplicationService {

    @Resource
    private BookDomainService bookDomainService;

    public Author addAuthor(Author author) {
        return bookDomainService.saveAuthor(author);
    }

    public Author updateAuthor(Author author) {
        Author check = bookDomainService.getAuthor(author.getId());
        if (check == null) {
            throw new ServerRuntimeException(ErrorCode.ResourceNotFound, "author does not exist");
        }
        return bookDomainService.saveAuthor(author);
    }

    public Book addBook(Book book) {
        return bookDomainService.saveBook(book);
    }

    public Book updateBook(Book book) {
        return bookDomainService.updateBook(book);
    }

    public Book getBook(long bookId) {
        return bookDomainService.getBook(bookId);
    }
}