package com.linghuyong.bookstore.domain.book.service;

import com.linghuyong.bookstore.domain.book.entity.Author;
import com.linghuyong.bookstore.domain.book.entity.Book;
import com.linghuyong.bookstore.domain.book.event.BookChangedEvent;
import com.linghuyong.bookstore.domain.book.repository.facade.BookRepositoryInterface;
import com.linghuyong.bookstore.domain.book.repository.po.AuthorPO;
import com.linghuyong.bookstore.domain.book.repository.po.BookPO;
import jakarta.annotation.Resource;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames={"book", "author"})
@Service
public class BookCacheService {

    @Resource(name= "bookRepositoryImpl")
    private BookRepositoryInterface bookRepo;

    @Resource
    private BookFactory bookFactory;

    @Resource
    private CacheManager cacheManager;

    private Cache getBookCache() {
        return cacheManager.getCache("book");
    }

    private Cache getAuthorCache() {
        return cacheManager.getCache("author");
    }


    @Cacheable("author")
    public Author getAuthor(long authorId) {
        AuthorPO authorPO = bookRepo.getAuthor(authorId);
        if (authorPO == null) {
            return null;
        }
        return bookFactory.createAuthor(authorPO);
    }

    @Cacheable("book")
    public Book getBook(long bookId) {
        BookPO bookPO = bookRepo.getBook(bookId, false);
        if (bookPO == null) {
            return null;
        }
        Author author = this.getAuthor(bookPO.getAuthorId());
        return bookFactory.createBook(bookPO, author);
    }

    @EventListener(BookChangedEvent.class)
    public void onApplicationEvent(BookChangedEvent event) {
        Book book = (Book) event.getSource();
        this.getBookCache().evict(book.getId());
    }
}
