package com.linghuyong.bookstore.domain.book.repository.persistence;

import com.linghuyong.bookstore.domain.book.repository.facade.BookRepositoryInterface;
import com.linghuyong.bookstore.domain.book.repository.mapper.AuthorDao;
import com.linghuyong.bookstore.domain.book.repository.mapper.BookDao;
import com.linghuyong.bookstore.domain.book.repository.po.AuthorPO;
import com.linghuyong.bookstore.domain.book.repository.po.BookPO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 * Book的Mybtis的Repo实现
 */
@Repository
public class BookRepositoryImpl implements BookRepositoryInterface {
    @Resource
    private BookDao bookDao;

    @Resource
    private AuthorDao authorDao;

    @Override
    public AuthorPO saveAuthor(AuthorPO authorPO) {
        authorDao.insertOrUpdate(authorPO);
        return authorPO;
    }

    @Override
    public AuthorPO getAuthor(long authorId) {
        return authorDao.selectById(authorId);
    }

    @Override
    public BookPO saveBook(BookPO bookPO) {
        bookDao.insertOrUpdate(bookPO);
        return bookPO;
    }

    @Override
    public BookPO getBook(long bookId, boolean selectForUpdate) {
        if (selectForUpdate) {
            /*
            跑测试用例才发现Sqlite不支持行锁，对于每个事务它锁上了整个表，其实是个文件锁。
            QueryWrapper<BookPO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", bookId).last("FOR UPDATE");
            return bookDao.selectOne(queryWrapper);
            */
        }
        return bookDao.selectById(bookId);
    }
}
