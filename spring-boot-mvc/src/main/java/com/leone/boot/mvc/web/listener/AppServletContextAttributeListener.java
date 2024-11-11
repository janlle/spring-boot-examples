package com.leone.boot.mvc.web.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;


/**
 * 向web上下文添加属性时触发监听器
 *
 * @author leone
 * @since 2018-05-13
 **/
@Slf4j
@WebListener
public class AppServletContextAttributeListener implements ServletContextAttributeListener {

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
