package com.leone.boot.data.mybatis.mapper;

import com.leone.boot.common.entity.User;
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

    @Insert({"<script>" +
      "insert into t_user(`account`, `password`, `age`, `description`, `deleted`, `create_time`) values" +
      "<foreach collection=\"users\" item=\"user\" separator=\",\">" +
      "(#{user.account}, #{user.password}, #{user.age}, #{user.description}, #{user.deleted}, #{user.createTime}) " +
      "</foreach>" +
      "</script>"})
    int insertBatch(@Param("users") List<User> users);

    @Delete("delete from t_user where user_id = #{userId}")
    int deleteById(@Param("userId") Long userId);

    @Delete("<script>" +
      "delete from t_user where user_id in " +
      "<foreach item='item' index='index' collection='userIds' open='(' separator=',' close=')'>" +
      "#{item}" +
      "</foreach>" +
      "</script>")
    int deleteByUserIds(@Param("userIds") List<Long> userIds);

    //@Update({"update t_user set account=#{user.account}, password=#{user.password}, age=#{user.age}, description=#{user.description}, create_time=#{user.createTime, jdbcType=TIMESTAMP}, deleted=#{user.deleted} where user_id = #{user.userId}"})
    int updateByIdSelective(User user);

    int updateById(User user);


    @Select("select * from t_user where user_id = #{userId}")
    User findByUserId(@Param("userId") Long userId);

    @Select("select * from t_user")
    List<User> findAll();

    @Select("select * from t_user limit ${start}, ${size}")
    List<User> page(@Param("start") int start, @Param("size") int size);

    @Select("select * from t_user where account like concat('%', #{name}, '%')")
    User selectByName(@Param("name") String name);


    @Update({"create table ${tableName}( " +
      " user_id int primary key not null auto_increment," +
      " account varchar(20) NOT NULL," +
      " password varchar(200) DEFAULT NULL," +
      " age int(1) DEFAULT 0," +
      " description int(1) DEFAULT 0," +
      " deleted date DEFAULT null," +
      " create_time currentTimestamp)" +
      " engine=innodb default charset=utf8"})
    void createUserTable(@Param("tableName") String tableName);

    @Update({"drop table if exists ${tableName}"})
    void dropTable(@Param("tableName") String tableName);



}
