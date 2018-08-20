package com.andy.mvc.web.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * 向web上下文添加属性时触发监听器
 *
 * @author: Leone
 * @since: 2018-05-13 10:54
 **/
@Slf4j
@WebListener
public class AppServletContextAttributeListener implements ServletContextAttributeListener {

    //当程序向application范围新增属性时触发此方法
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        ServletContext servletContext = event.getServletContext();
        String name = event.getName();
        Object value = event.getValue();
        log.info(servletContext + "范围内新增了key值为=" + name + "value值=" + value);
    }

    //当程序向application范围移除属性时触发此方法
    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        ServletContext application = event.getServletContext();
        //获取移除的属性名与值
        String name = event.getName();
        Object value = event.getValue();
        System.out.println(application + "范围内移除了name值为=" + name + "value值=" + value);
    }

    //当程序向application范围替换属性时触发此方法
    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        ServletContext application = event.getServletContext();
        //获取替换的属性名与值
        String name = event.getName();
        Object value = event.getValue();
        System.out.println(application + "范围内替换了name值为=" + name + "value值=" + value);
    }
}
