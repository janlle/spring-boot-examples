package com.leone.boot.data.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author leone
 * @since 2021-10-27
 **/
public class DruidPoolExample {

    private static DataSource dataSource;

    private static final Logger log = LoggerFactory.getLogger(DruidPoolExample.class);

    public static void init() {
        try (InputStream is = DruidPoolExample.class.getClassLoader().getResourceAsStream("mysql.properties")) {
            Properties props = new Properties();
            props.load(is);

            props.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
            props.setProperty("username", "root");
            props.setProperty("password", "123456");
            props.setProperty("url", "jdbc:mysql://localhost:3306/db01?useSSL=false");
            // 是否自动提交
            props.setProperty("defaultAutoCommit", "true");
            // 最大连接数
            props.setProperty("maxActive", "20");
            // 最小空闲连接数
            props.setProperty("minIdle", "5");
            // 初始化连接数
            props.setProperty("initialSize", "10");
            // 最大超时时间
            props.setProperty("maxWait", "300000");
            // 申请连接时检测连接是否有效
            props.setProperty("testOnBorrow", "true");
            // 归还连接时检测连接是否有效
            props.setProperty("testOnReturn", "true");
            // 检测连接是否有效
            props.setProperty("validationQuery", "SELECT 1");
            // 测试连接超时时间
            props.setProperty("validationQueryTimeout", "5000");
            dataSource = DruidDataSourceFactory.createDataSource(props);
            log.info("init database connection success.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

    }

    public void correctMethod() throws Exception {
        for (int i = 0; i < 1000; i++) {
            TimeUnit.SECONDS.sleep(12);
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into db01.student(name, age) values (?, ?)");
            ps.setString(1, UUID.randomUUID().toString());
            ps.setInt(2, (int) (Math.random() * 100));
            System.out.println(LocalDateTime.now().toString() + ps.execute());
        }
    }

    public void errorMethod() throws Exception {
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into db01.student(name, age) values (?, ?)");

        for (int i = 0; i < 1000; i++) {
            TimeUnit.SECONDS.sleep(2);
            // ps.setString(1, UUID.randomUUID().toString());
            // ps.setInt(2, (int) (Math.random() * 100));
            // ps.addBatch();
            System.out.println(LocalDateTime.now() + Arrays.toString(ps.executeBatch()));
        }
    }

    public void testConnection() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Connection c = dataSource.getConnection();
                    boolean b = c.createStatement().execute("select version()");
                    System.out.println(Thread.currentThread().getName() + " " + b + " " + c);
                    c.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }).start();
        }
    }

}
