package com.leone.boot.mvc.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.http.HttpServletRequest;;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 同步servlet
 *
 * @author leone
 * @since 2018-05-22
 **/
@Slf4j
@WebServlet(name = "syncServlet", urlPatterns = {"/sync"}, asyncSupported = false)
public class SyncServlet extends HttpServlet {

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
