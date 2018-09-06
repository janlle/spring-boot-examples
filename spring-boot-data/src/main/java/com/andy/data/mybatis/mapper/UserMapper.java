package com.andy.data.mybatis.mapper;

import com.andy.data.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Leone
 * @since 2018-05-11 18:43
 **/
@Mapper
public interface UserMapper {

    @Insert("insert into t_user(`user_id`, `account`, `create_time`, `deleted`, `password`, `age`, `email`, `sex`) values(#{userId},#{account},#{createTime},#{deleted},#{password},#{age},#{email}，#{sex})")
    int insert(User user);

    int insertSelective(User user);

    int insertList(@Param("users") List<User> users);

    @Delete("delete from t_user where user_id = #{userId}")
    int deleteById(@Param("userId") Long userId);


    int updateByIdSelective(User user);

    int updateById(User user);


    @Select("select * from t_user where user_id = #{userId}")
    User findByUserId(@Param("userId") Long userId);

    @Select("select * from t_user")
    List<User> findAll();

}
