package com.linghuyong.bookstore.domain.book.service;

import com.linghuyong.bookstore.domain.book.entity.Author;
import com.linghuyong.bookstore.domain.book.entity.Book;
import com.linghuyong.bookstore.domain.book.repository.po.AuthorPO;
import com.linghuyong.bookstore.domain.book.repository.po.BookPO;
import org.springframework.stereotype.Service;

@Service
public class BookFactory {

    public AuthorPO createAuthorPO(Author author) {
        AuthorPO authorPO = new AuthorPO();
        authorPO.setId(author.getId());
        authorPO.setName(author.getName());
        return authorPO;
    }

    public Author createAuthor(AuthorPO authorPO) {
        Author author = new Author();
        author.setId(authorPO.getId());
        author.setName(authorPO.getName());
        return author;
    }

    public BookPO createBookPO(Book book) {
        BookPO bookPO = new BookPO();
        bookPO.setId(book.getId());
        bookPO.setTitle(book.getTitle());
        if (book.getAuthor() != null) {
            bookPO.setAuthorId(book.getAuthor().getId());
        }
        bookPO.setDescription(book.getDescription());
        bookPO.setEnable(book.getEnable());
        bookPO.setClassification(book.getClassification());
        bookPO.setPrice(book.getPrice());
        bookPO.setStock(book.getStock());
        return bookPO;
    }

    public Book createBook(BookPO bookPO, Author author) {
        Book book = new Book();
        book.setId(bookPO.getId());
        book.setTitle(bookPO.getTitle());
        book.setAuthor(author);
        book.setDescription(bookPO.getDescription());
        book.setEnable(bookPO.getEnable());
        book.setPrice(bookPO.getPrice());
        book.setClassification(bookPO.getClassification());
        book.setStock(bookPO.getStock());
        return book;
    }
}
