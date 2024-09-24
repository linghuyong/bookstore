package com.linghuyong.bookstore.domain.book.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linghuyong.bookstore.domain.book.repository.po.AuthorPO;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends BaseMapper<AuthorPO> {

}
