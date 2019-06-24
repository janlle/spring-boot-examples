package com.leone.boot.common.io.array;

import java.io.*;

/**
 * <p>ByteOutputStream 的 write 方法是写进内存的
 *
 * @author leone
 * @since 2019-06-24
 **/
public class ByteArrayInputOutputStream {

    /**
     * 1.使用 FileInputStream 读取文件到 ByteArrayOutputStream
     * 2.从 ByteArrayOutputStream 读取字节到 ByteArrayInputStream
     * 3.从 ByteArrayInputStream 读取数据到 FileOutputStream 保存到磁盘
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("d:/a.txt");
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

        int i;
        byte[] bytes = new byte[1024];
        while ((i = fileInputStream.read(bytes)) != -1) {
            arrayOutputStream.write(bytes, 0, i);
        }

        FileOutputStream fileOutputStream = new FileOutputStream("d:/b.txt");
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());

        while ((i = arrayInputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, i);
        }

        fileOutputStream.flush();
        fileInputStream.close();
        fileOutputStream.close();
        arrayInputStream.close();
        arrayOutputStream.close();
    }

}
