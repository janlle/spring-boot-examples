package com.leone.security.mapper;

import com.leone.security.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Leone
 * @since 2018-07-07
 **/
@Mapper
public interface UserMapper {

    @Select("select * from t_user where account = ${account} and password = ${password} limit 1")
    List<User> login(@Param("account") String account, @Param("password")String password);

}
