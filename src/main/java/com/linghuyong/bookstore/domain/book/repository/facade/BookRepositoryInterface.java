package com.linghuyong.bookstore.domain.book.repository.facade;


import com.linghuyong.bookstore.domain.book.repository.po.AuthorPO;
import com.linghuyong.bookstore.domain.book.repository.po.BookPO;

public interface BookRepositoryInterface {

    AuthorPO saveAuthor(AuthorPO authorPO);

    AuthorPO getAuthor(long authorId);

    BookPO saveBook(BookPO bookPO);

    BookPO getBook(long bookId, boolean selectForUpdate);
}