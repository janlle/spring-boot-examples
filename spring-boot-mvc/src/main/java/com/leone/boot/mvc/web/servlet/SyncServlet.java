package com.leone.boot.mvc.web.servlet;


import com.leone.boot.mvc.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 同步servlet
 *
 * @author leone
 * @since 2018-05-22
 **/
@WebServlet(name = "syncServlet", urlPatterns = {"/sync"}, asyncSupported = false)
public class SyncServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(SyncServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        try {
            doSomething();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("sync servlet exec time: {}", end - start);
        resp.getWriter().write("sync servlet exec time: " + (end - start));
    }

    /**
     * 自定义耗时业务逻辑
     *
     * @throws InterruptedException
     */
    private void doSomething() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(3000);
    }


}
