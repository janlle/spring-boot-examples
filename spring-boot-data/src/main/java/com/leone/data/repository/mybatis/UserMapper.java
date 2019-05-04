package com.leone.data.repository.mybatis;

import com.leone.data.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author leone
 * @since 2018-05-11
 **/
@Mapper
public interface UserMapper {

    @Insert("insert into t_user(`account`, `password`, `age`, `description`, `deleted`, `create_time`) values(#{account},#{password},#{age},#{description},#{deleted},#{createTime})")
    int insert(User user);

    int insertSelective(User user);

    int insertBatch(@Param("users") List<User> users);

    @Delete("delete from t_user where user_id = #{userId}")
    int deleteById(@Param("userId") Long userId);

    int updateByIdSelective(User user);

    int updateById(User user);

    @Select("select * from t_user where user_id = #{userId}")
    User findByUserId(@Param("userId") Long userId);

    @Select("select * from t_user")
    List<User> findAll();

}
