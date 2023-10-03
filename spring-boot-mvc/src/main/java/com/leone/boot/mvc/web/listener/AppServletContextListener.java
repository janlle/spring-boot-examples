package com.leone.boot.mvc.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author leone
 * @since 2018-05-13
 **/
@WebListener()
public class AppServletContextListener implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(AppServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("web 上下文启动！");
        //web应用上下文实例
        ServletContext application = sce.getServletContext();

        String driver = application.getInitParameter("driver");
        String url = application.getInitParameter("url");
        String username = (String) application.getAttribute("username");
        String password = (String) application.getAttribute("password");
        if (driver != null && url != null && username != null && password != null) {
            log.info("用户名:{}---密码:{}---url:{}", username, password, url);
            try {
                try {
                    Class.forName(driver);
                } catch (ClassNotFoundException e) {
                    log.error("获取数据库连接失败！");
                    e.printStackTrace();
                }
                Connection connection = DriverManager.getConnection(url, username, password);
                application.setAttribute("connection", connection);
            } catch (Exception e) {
                log.error("加载数据库驱动失败！");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("web 上下文销毁！");
        ServletContext application = sce.getServletContext();
        Connection connection = (Connection) application.getAttribute("connection");
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("关闭数据库连接失败！");
            }
        }
    }
}
