package com.leone.boot.common.io.buffer;

import java.io.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-24
 **/
public class BufferedInputOutputStream {

    public static void main(String[] args) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("d:/a.txt"));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("d:/b.txt"));

        int i;
        byte[] bytes = new byte[1024];
        while ((i = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, i);
        }
        inputStream.close();
        outputStream.close();
    }


}
