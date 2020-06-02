package com.leone.boot.shiro.mapper;

import com.leone.boot.shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    int insert(User record);

    int insertSelective(User record);

    User findAllPermissionByAccount(@Param("account") String account);

    @Select("select * from sys_user where account = #{account} and status = 1")
    User findUserByAccount(@Param("account") String account);

    @Select("select * from sys_user where account = #{account} and password = #{password} and status = 1")
    User login(@Param("account") String account, @Param("password") String password);

}