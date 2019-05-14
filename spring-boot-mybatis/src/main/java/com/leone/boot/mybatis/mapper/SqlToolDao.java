package com.leone.boot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-04
 **/
@Mapper
public interface SqlToolDao {

    @Update({"create table ${tableName}( " +
            " user_id int primary key not null auto_increment," +
            " account varchar(20) NOT NULL," +
            " password varchar(200) DEFAULT NULL," +
            " age int(1) DEFAULT 0," +
            " description int(1) DEFAULT 0," +
            " deleted date DEFAULT null," +
            " create_time currentTimestamp)" +
            " engine=innodb default charset=utf8"})
    void createTable(@Param("tableName") String tableName);


    @Update({"drop table if exists ${tableName}"})
    void dropTable(@Param("tableName") String tableName);

}
