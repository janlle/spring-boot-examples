package com.andy.data.mybatis.mapper;

import com.andy.data.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: lyon
 * @since: 2018-05-11 18:43
 **/
@Mapper
public interface UserMapper {

//    @Insert("insert into t_user(birthday,email,password,salary,token,username) values(#{birthday},#{email},#{password},#{salary},#{token},#{username})")
    int insert(User user);

    int insertSelective(User user);

    int insertList(@Param("users") List<User> users);

    @Delete("delete from t_user where userId = #{userId}")
    int deleteById(@Param("userId") long userId);


    int updateByIdSelective(User user);

    int updateById(User user);


    @Select("select * from t_user where id = #{userId}")
    User findByUserId(@Param("userId") long userId);

    @Select("select * from t_user")
    List<User> findAll();

}
