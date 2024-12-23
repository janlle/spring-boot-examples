package com.leone.boot.learn.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * <p>
 *
 * @author leone
 * @since 2024-12-23
 **/
public class TomcatApp {

    private static final Logger log = LoggerFactory.getLogger(TomcatApp.class);

    public static void main(String[] args) throws LifecycleException {
        springMvcWeb(9000);
    }

    public static void servletWeb(int port) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();
        Context context = tomcat.addContext("/tomcat", null);

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TomcatAppConfig.class);
        // 从context中获取servletContext，并将applicationContext设置为其属性，以便在servlet中再次取得
        context.getServletContext().setAttribute("applicationContext", applicationContext);

        Wrapper loginServlet = Tomcat.addServlet(context, "loginServlet", new LoginServlet());
        loginServlet.addMapping("/login");

        tomcat.start();
    }

    public static void springMvcWeb(int port) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();

        Context context = tomcat.addContext("/mvc", null);

        // WebApplicationContext 来自spring-web模块
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        // 加载配置类方式略有不同
        webApplicationContext.register(TomcatAppConfig.class);
        // 主动设置servletContext的引用
        webApplicationContext.setServletContext(context.getServletContext());
        webApplicationContext.refresh();

        // DispatcherServlet 来自spring-webmvc模块
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        Wrapper servlet = Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet);
        servlet.setLoadOnStartup(1);
        // 前端控制器模式，拦截所有请求
        servlet.addMapping("/*");

        tomcat.start();
    }


}
