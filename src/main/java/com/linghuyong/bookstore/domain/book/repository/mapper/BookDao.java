package com.linghuyong.bookstore.domain.book.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linghuyong.bookstore.domain.book.repository.po.BookPO;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends BaseMapper<BookPO> {

}
