package com.leone.boot.mybatis.mapper;


import com.leone.boot.common.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2018-03-02
 **/
@Mapper
public interface UserMapper {

    @Insert("insert into t_user(`account`, `password`, `age`, `description`, `deleted`, `create_time`) values (#{account},#{password},#{age},#{description},#{deleted},#{createTime})")
    int insert(User user);

    @Insert({"<script>" +
            "insert into t_user(`account`, `password`, `age`, `description`, `deleted`, `create_time`) values" +
            "<foreach collection=\"users\" item=\"user\" separator=\",\">" +
            "(#{user.account}, #{user.password}, #{user.age}, #{user.description}, #{user.deleted}, #{user.createTime}) " +
            "</foreach>" +
            "</script>"})
    int insertBatch(@Param("users") List<User> users);


    @Delete("delete from t_user where user_id = #{userId}")
    int deleteByUserId(@Param("userId") Long userId);

    @Delete("<script>" +
            "delete from t_user where user_id in " +
            "<foreach item='item' index='index' collection='userIds' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    int deleteByUserIds(@Param("userIds") List<Long> userIds);


    @Select("select * from t_user where user_id = #{userId}")
    User findByUserId(@Param("userId") Long userId);

    @Select("select * from t_user limit ${start}, ${size}")
    List<User> page(@Param("start") int start, @Param("size") int size);

    @Select("select * from t_user")
    List<User> findAll();


    @Update({"update t_user set account=#{user.account}, password=#{user.password}, age=#{user.age}, description=#{user.description}, create_time=#{user.createTime, jdbcType=TIMESTAMP}, deleted=#{user.deleted} where user_id = #{user.userId}"})
    int update(@Param("user") User user);

    @Update({"<script>" +
            "<foreach collection=\"users\" item=\"item\" separator=\";\">" +
            " update t_user set" +
            " account=#{item.account}," +
            " password=#{item.password}," +
            " age=#{item.age}," +
            " description=#{item.description}," +
            " create_time=#{item.createTime}," +
            " deleted=#{item.deleted}" +
            " where user_id=#{item.userId}" +
            "</foreach>" +
            "</script>"})
    int updateBatch(@Param("users") List<User> users);

}
