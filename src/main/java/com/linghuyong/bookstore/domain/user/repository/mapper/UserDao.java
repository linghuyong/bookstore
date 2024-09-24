package com.linghuyong.bookstore.domain.user.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linghuyong.bookstore.domain.user.repository.po.UserPO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseMapper<UserPO> {

}
