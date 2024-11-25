package com.leone.boot.data.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * <p>
 *
 * @author leone
 * @since 2021-06-02
 **/
public class ClickhouseJdbcClient {

    private static Connection connection;

    public static void init() {
        try {
            Class.forName("com.clickhouse.jdbc.ClickHouseDriver");
            Connection connection = DriverManager.getConnection("jdbc:clickhouse://vm.rm:8123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectVersion(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select count(*) from db1.person");
        if (rs.next()) {
            int count = rs.getInt(1);
            System.out.println(count);
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        selectVersion(connection);
    }

}
