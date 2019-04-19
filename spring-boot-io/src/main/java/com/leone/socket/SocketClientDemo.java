package com.leone.socket;

import java.io.*;
import java.net.Socket;

/**
 * <p>
 *
 * @author leone
 * @since 2019-04-10
 **/
public class SocketClientDemo {

    private static int port = 8888;

    private static String host = "localhost";

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(host, port);

        InputStream inputStream = socket.getInputStream();

        OutputStream outputStream = socket.getOutputStream();

        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println("hello word");
        printWriter.flush();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println("server: " + line);
        }
        bufferedReader.close();

    }

}
