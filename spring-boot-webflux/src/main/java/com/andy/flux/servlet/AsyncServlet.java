package com.andy.flux.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Leone
 * @since 2018-05-22
 **/
@Slf4j
@WebServlet(name = "asyncServlet", urlPatterns = {"/async"}, asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = System.currentTimeMillis();

        AsyncContext asyncContext = req.startAsync();

        CompletableFuture.runAsync(() -> doSomething(asyncContext, asyncContext.getRequest(), asyncContext.getResponse()));

        long end = System.currentTimeMillis();
        log.info("time:{}", end - start);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }


    private void doSomething(AsyncContext asyncContext, ServletRequest req, ServletResponse resp) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 业务代码结束
        asyncContext.complete();

    }


}
