package com.andy.shiro.mapper;

import com.andy.shiro.entity.rbac.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-21 15:58
 **/
public interface UserMapper {

//    @Select("select * from sys_user where username = #{username}")
    User findByUsername(@Param("username") String username);


}
