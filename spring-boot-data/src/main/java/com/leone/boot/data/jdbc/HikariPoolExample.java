package com.leone.boot.data.jdbc;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <p>
 *
 * @author leone
 * @since 2021-10-08
 **/
public class HikariPoolExample {

    private static final Logger log = LoggerFactory.getLogger(HikariPoolExample.class);

    private static DataSource dataSource;

    public static void sshTunnel() {
        try {
            Session session = new JSch().getSession("root", "xxx", 22);
            session.setPassword("xxx");
            session.setConfig("StrictHostKeyChecking", "no");
            // 设置SSH隧道，从本地端口连接到远程数据库服务器的指定端口
            session.setPortForwardingL(5432, "xxx", 5432);
            // 开启SSH隧道
            session.connect();
            System.out.println(session.getServerVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        if (dataSource != null) {
            return;
        }
        try (InputStream is = HikariPoolExample.class.getClassLoader().getResourceAsStream("postgresql.properties")) {
            Properties props = new Properties();
            props.load(is);
            HikariConfig config = new HikariConfig(props);
            // 设置名称
            config.setPoolName("hikari");
            // 是否自动提交
            config.setAutoCommit(true);
            // 最小空闲连接，默认值10
            config.setMinimumIdle(10);
            // 最大连接数
            config.setMaximumPoolSize(20);
            // 空闲连接超时时间，默认值600000
            config.setIdleTimeout(600000);
            // 连接最大存活时间
            config.setMaxLifetime(30 * 60 * 1000);
            // 连接超时时间：毫秒
            config.setConnectionTimeout(10000);
            // 测试连接是否可用的查询
            config.setConnectionTestQuery("select 1");
            dataSource = new HikariDataSource(config);
            log.info("init database connection success.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param sql sql
     */
    public static void exec(String sql) {
        try (Connection c = dataSource.getConnection()) {
            log.info("exec: {}", sql);
            c.createStatement().execute(sql);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * @param sql sql
     * @return res
     */
    public static ResultSet query(String sql) {
        try {
            Connection conn = dataSource.getConnection();
            log.info("query: {}", sql);
            return conn.createStatement().executeQuery(sql);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static void isConnected() {
        try (ResultSet query = query("select version()")) {
            if (query != null && query.next()) {
                System.out.println(query.getString(1));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        isConnected();
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                System.out.println("thread");
            }).start();
        }
    }

}
