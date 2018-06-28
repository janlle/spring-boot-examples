package com.andy.data.mybatis.mapper;

import com.andy.data.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-05-11 18:43
 **/
@Mapper
public interface UserMapper {

//    @Insert("insert into t_user(birthday,email,password,salary,token,username) values(#{birthday},#{email},#{password},#{salary},#{token},#{username})")
    int insert(User user);

    int insertSelective(User user);

//    @Delete("delete from t_user where id = #{id}")
    int deleteById(@Param("id") long id);

    int updateByIdSelective(User user);

    int updateById(User user);

//    @Select("select * from t_user where id = #{id}")
    User selectById(@Param("id") long id);

    int insertList(@Param("users") List<User> users);

    @Select("select * from t_user")
    List<User> selectAll();

    User findByUserId(@Param("userId") long userId);
}
