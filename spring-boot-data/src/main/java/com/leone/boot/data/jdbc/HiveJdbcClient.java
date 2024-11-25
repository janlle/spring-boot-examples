package com.leone.boot.data.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * <p> jdbc连接hive
 *
 * @author leone
 * @since 2018-06-17
 **/
public class HiveJdbcClient {

    /**
     * 1,123.25.51.81,https://www.google.com,2019-01-21
     * 2,123.25.51.82,https://www.youku.com,2019-01-22
     * 3,123.25.51.83,https://www.taobao.com,2019-01-23
     * 4,123.25.51.84,https://www.baidu.com,2019-01-24
     * 5,123.25.51.85,https://www.jd.com,2019-01-26
     * 6,123.25.51.86,https://www.tianmao.com,2019-01-27
     */
    private Connection connection;

    private Statement statement;

    public void init() throws Exception {
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        connection = DriverManager.getConnection("jdbc:hive2://192.168.1.101:10000/db1", "root", "");
        statement = connection.createStatement();
    }

    /**
     * 查询表数据
     *
     * @throws Exception ex
     */
    public void select() throws Exception {
        ResultSet result = statement.executeQuery("select * from t_log");
        System.out.println("-----------------------------------------------------");
        System.out.println("id\tip\turl\ttime");
        System.out.println("-----------------------------------------------------");
        while (result.next()) {
            int id = result.getInt(1);
            String ip = result.getString(2);
            String url = result.getString(3);
            String time = result.getString(4);
            System.out.println("|" + id + "\t" + ip + "\t" + url + "\t" + time + "|");
        }
        System.out.println("-----------------------------------------------------");
    }


    /**
     * 删除表
     *
     * @throws Exception ex
     */
    public void dropTable() throws Exception {
        String sql = "drop table if exists t_log";
        boolean flag = statement.execute(sql);
        System.out.println("running sql:" + sql + (flag ? " " : "failed success"));
    }


    /**
     * 删除数据库
     *
     * @throws Exception ex
     */
    public void dropDatabase() throws Exception {
        String sql = "drop database if exists db1";
        boolean flag = statement.execute(sql);
        System.out.println("running sql:" + sql + (flag ? " " : "failed success"));
    }


    /**
     * 创建表
     *
     * @throws Exception ex
     */
    public void createTable() throws Exception {
        String sql = "create table t_log(id int, ip string, url string, time string)" +
                " row format delimited fields terminated by ','";
        boolean flag = statement.execute(sql);
        System.out.println("running sql:" + sql + (flag ? " " : "failed success"));
    }


    /**
     * 查询所有表
     *
     * @throws Exception ex
     */
    public void showTable() throws Exception {
        ResultSet resultSet = statement.executeQuery("show tables");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
    }


    /**
     * 加载数据
     *
     * @throws Exception ex
     */
    public void loadData() throws Exception {
        String sql = "load data inpath file:///data/app.log overwrite into table t_log";
        boolean flag = statement.execute(sql);
        System.out.println("running sql:" + sql + (flag ? " " : "failed success"));
    }


    /**
     * 统计查询（会运行 mapReduce 作业）
     *
     * @throws Exception ex
     */
    public void selectCount() throws Exception {
        String sql = "select count(1) from t_log";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
        }
        boolean flag = statement.execute(sql);
        System.out.println("running sql:" + sql + (flag ? " " : "failed success"));
    }

}
