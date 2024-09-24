package com.linghuyong.bookstore.domain.book.service;

import com.linghuyong.bookstore.domain.book.entity.Author;
import com.linghuyong.bookstore.domain.book.entity.Book;
import com.linghuyong.bookstore.domain.book.event.BookChangedEvent;
import com.linghuyong.bookstore.domain.book.repository.facade.BookRepositoryInterface;
import com.linghuyong.bookstore.domain.book.repository.po.AuthorPO;
import com.linghuyong.bookstore.domain.book.repository.po.BookPO;
import com.linghuyong.bookstore.infrastructure.common.exception.ServerRuntimeException;
import com.linghuyong.bookstore.interfaces.dto.ErrorCode;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class BookDomainService {

    @Resource(name= "bookRepositoryImpl")
    private BookRepositoryInterface bookRepo;
    @Resource
    private BookFactory bookFactory;
    @Resource
    private BookCacheService bookCacheService;

    @Resource
    private ApplicationContext context;

    public Author saveAuthor(Author author) {
        AuthorPO ret = bookRepo.saveAuthor(bookFactory.createAuthorPO(author));
        return bookFactory.createAuthor(ret);
    }

    public Author getAuthor(long authorId) {
        return bookCacheService.getAuthor(authorId);
    }

    public Book saveBook(Book book) {
        Author author = bookCacheService.getAuthor(book.getAuthor().getId());
        if (author == null) {
            throw new ServerRuntimeException(ErrorCode.ResourceNotFound, "author does not exist");
        }

        BookPO bookPO = bookRepo.saveBook(bookFactory.createBookPO(book));
        return bookFactory.createBook(bookPO, author);
    }

    public Book updateBook(Book book) {
        Book oldBook = bookCacheService.getBook(book.getId());
        if (oldBook == null) {
            throw new ServerRuntimeException(ErrorCode.ResourceNotFound, "book does not exist");
        }

        // 根据需求对各个修改字段处理逻辑
        if (book.getAuthor() != null) {
            Author author = bookCacheService.getAuthor(book.getAuthor().getId());
            if (author == null) {
                throw new ServerRuntimeException(ErrorCode.ResourceNotFound, "author does not exist");
            }
            book.setAuthor(author);
        }
        book.setId(oldBook.getId());


        BookPO bookPO = bookRepo.saveBook(bookFactory.createBookPO(book));
        Book ret = bookFactory.createBook(bookPO, book.getAuthor());
        context.publishEvent(new BookChangedEvent(ret));
        return ret;
    }

    // 修改图书库存
    public Book changeBookStock(long bookId, int delta) {
        BookPO bookPO = bookRepo.getBook(bookId, true);
        if (bookPO == null) {
            throw new ServerRuntimeException(ErrorCode.ResourceNotFound, "book does not exist");
        }
        // 这里不需要处理author，所以就不查询了
        Book book = bookFactory.createBook(bookPO, null);
        if (!book.changeStock(delta)) {
            throw new ServerRuntimeException(ErrorCode.OutOfStock, "book is out of stock");
        }
        bookRepo.saveBook(bookFactory.createBookPO(book));
        context.publishEvent(new BookChangedEvent(book));
        return book;
    }

    public Book getBook(long bookId) {
        return bookCacheService.getBook(bookId);
    }
}
