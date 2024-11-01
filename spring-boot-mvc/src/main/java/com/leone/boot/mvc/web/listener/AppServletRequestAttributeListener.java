package com.leone.boot.mvc.web.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;


/**
 * @author leone
 * @since 2018-05-13
 **/
@Slf4j
@WebListener
public class AppServletRequestAttributeListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

    }
}
