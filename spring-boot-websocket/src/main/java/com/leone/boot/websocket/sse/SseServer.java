package com.leone.boot.websocket.sse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author leone
 * @since 2018-07-23
 **/
@WebServlet(name = "/sseServlet", urlPatterns = "/sse")
public class SseServer extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/event-stream");
        resp.setCharacterEncoding("utf-8");

        for (int i = 0; i < 5; i++) {
            resp.getWriter().write("data:" + i + "\n\n");
            resp.getWriter().flush();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
