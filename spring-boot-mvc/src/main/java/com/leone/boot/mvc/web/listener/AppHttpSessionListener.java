package com.leone.boot.mvc.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author leone
 * @since 2018-05-13
 **/
@WebListener()
public class AppHttpSessionListener implements HttpSessionListener {

    private static final Logger log = LoggerFactory.getLogger(AppHttpSessionListener.class);


    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
