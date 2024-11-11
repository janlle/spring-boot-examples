package com.leone.boot.mvc.web.servlet;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.http.HttpServletRequest;;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 异步非阻塞servlet
 *
 * @author leone
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
        log.info("async servlet exec time: {}", end - start);
        resp.getWriter().write("async servlet exec time: " + (end - start));
    }

    /**
     * 自定义耗时业务逻辑
     */
    private void doSomething(AsyncContext asyncContext, ServletRequest req, ServletResponse resp) {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 业务代码结束
        asyncContext.complete();
    }


}
