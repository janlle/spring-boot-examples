package com.leone.boot.mvc.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @author leone
 * @since 2018-05-13
 **/
@WebListener
public class AppServletRequestAttributeListener implements ServletRequestListener {

    private static final Logger log = LoggerFactory.getLogger(AppServletRequestAttributeListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

    }
}
