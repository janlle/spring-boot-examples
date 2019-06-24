package com.leone.boot.common.io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-24
 **/
public class FileInputOutputStream {

    static int count = 0;

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File("d:/a.txt"));
        FileOutputStream outputStream = new FileOutputStream(new File("d:/b.txt"));

        // 一个字节一个字节的读取
        //int b;
        //while ((b = inputStream.read()) != -1) {
        //    count++;
        //    outputStream.write(b);
        //}

        // 使用一个字节数组来读取
        int i;
        byte[] bytes = new byte[1024];
        while ((i = inputStream.read(bytes, 0, bytes.length)) != -1) {
            count++;
            outputStream.write(bytes, 0, i);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
        System.out.println(count);
    }

}
