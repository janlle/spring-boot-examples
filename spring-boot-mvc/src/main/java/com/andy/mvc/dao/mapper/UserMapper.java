package com.andy.mvc.dao.mapper;

import com.andy.mvc.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Leone
 * @since: 2018-04-10 11:07
 **/
// @Mapper 这里可以使用@Mapper注解，但是每个mapper都加注解比较麻烦，所以统一配置@MapperScan在扫描路径在application类中
public interface UserMapper {

    @Select("SELECT * FROM t_user WHERE id = #{id}")
    User getUserById(Integer id);

    @Select("SELECT * FROM t_user")
    List<User> getUserList();

    @Insert("insert into t_user(birthday, email, salary, username) values(#{birthday}, #{email}, #{salary},#{username})")
    int insert(User user);

    @Update("UPDATE t_user SET username = #{user.username} , age = #{user.age} WHERE id = #{id}")
    int update(@Param("id") Integer id, @Param("user") User user);

    @Delete("DELETE from t_user where id = #{id} ")
    int delete(Integer id);



}
