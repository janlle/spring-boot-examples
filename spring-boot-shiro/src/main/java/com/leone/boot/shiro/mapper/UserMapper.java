package com.leone.boot.shiro.mapper;

import com.leone.boot.shiro.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    User findByAccount(@Param("account") String account);

    User login(@Param("account") String account, @Param("password") String password);

}