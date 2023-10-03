package com.leone.boot.mvc.web.listener;


import com.leone.boot.mvc.config.SpringTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * 向web上下文添加属性时触发监听器
 *
 * @author leone
 * @since 2018-05-13
 **/
@WebListener
public class AppServletContextAttributeListener implements ServletContextAttributeListener {

    private static final Logger log = LoggerFactory.getLogger(AppServletContextAttributeListener.class);

    // 当程序向application范围新增属性时触发此方法
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        ServletContext servletContext = event.getServletContext();
        String name = event.getName();
        Object value = event.getValue();
        log.info("servletContext: {} added attribute key: {} value: {}", servletContext, name, value);
    }

    // 当程序向application范围移除属性时触发此方法
    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        ServletContext servletContext = event.getServletContext();
        String name = event.getName();
        Object value = event.getValue();
        log.info("servletContext: {} removed attribute key: {} value: {}", servletContext, name, value);
    }

    // 当程序向application范围替换属性时触发此方法
    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        ServletContext servletContext = event.getServletContext();
        String name = event.getName();
        Object value = event.getValue();
        log.info("servletContext: {} replaced attribute key: {} value: {}", servletContext, name, value);

    }
}
