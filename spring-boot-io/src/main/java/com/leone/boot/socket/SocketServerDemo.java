package com.leone.boot.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>
 *
 * @author leone
 * @since 2019-04-10
 **/
public class SocketServerDemo {

    private static int port = 8888;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);

        Socket accept = serverSocket.accept();

        InputStream inputStream = accept.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(serverSocket.getInetAddress() + ": " + line);
        }

        OutputStream outputStream = accept.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println("hello i am server");
        printWriter.flush();

        accept.shutdownInput();
        accept.shutdownOutput();

    }

}
