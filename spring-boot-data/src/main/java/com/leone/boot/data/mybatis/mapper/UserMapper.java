package com.leone.boot.data.mybatis.mapper;

import com.leone.boot.common.entity.User;
import com.leone.boot.data.mybatis.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author leone
 * @since 2018-05-11
 **/
@Mapper
public interface UserMapper {

    @Insert("insert into user_info(`user_id`, `username`, `password`, `age`, `deleted`) values(#{userId}, #{username},#{password},#{age},#{deleted})")
    int insert(UserInfo user);

    //@Insert({"<script>" +
    //  "insert into t_user(`account`, `password`, `age`, `description`, `deleted`, `create_time`) values" +
    //  "<foreach collection=\"users\" item=\"user\" separator=\",\">" +
    //  "(#{user.account}, #{user.password}, #{user.age}, #{user.description}, #{user.deleted}, #{user.createTime}) " +
    //  "</foreach>" +
    //  "</script>"})
    int insertBatch(@Param("users") List<User> users);

    @Delete("<script>" +
      "delete from user_info where user_id in " +
      "<foreach item='item' index='index' collection='userIds' open='(' separator=',' close=')'>" +
      "#{item}" +
      "</foreach>" +
      "</script>")
    int deleteByUserIds(@Param("userIds") List<Long> userIds);

    int updateByIdSelective(User user);

    @Select("select * from user_info where user_id = #{userId}")
    User findByUserId(@Param("userId") String userId);

    @Select("select * from user_info")
    List<User> selectAll();

    @Update({"create table user_info ( " +
      " user_id int primary key not null auto_increment," +
      " account varchar(20) NOT NULL," +
      " password varchar(200) DEFAULT NULL," +
      " age int(1) DEFAULT 0," +
      " description int(1) DEFAULT 0," +
      " deleted date DEFAULT null," +
      " create_time currentTimestamp)" +
      " engine=innodb default charset=utf8 "})
    void createTable();

    @Update({"drop table if exists user_info"})
    void dropTable();


}
