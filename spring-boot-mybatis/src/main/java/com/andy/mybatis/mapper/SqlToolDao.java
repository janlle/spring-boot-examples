package com.andy.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SqlToolDao {


    @Update({"create table ${tableName}( user_id int PRIMARY KEY NOT NULL AUTO_INCREMENT," +
            " title varchar(20) NOT NULL," +
            " detail varchar(200) DEFAULT NULL," +
            " status int(1) DEFAULT 0," +
            " priority int(1) DEFAULT 0," +
            " description date DEFAULT null," +
            " create_time currentTimestamp)" +
            " ENGINE=Innodb DEFAULT CHARSET=utf8"})
    void createTable(@Param("tableName") String tableName);


    @Update({"drop table if exists ${tableName}"})
    void dropTable(@Param("tableName") String tableName);

}
