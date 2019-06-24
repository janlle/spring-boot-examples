package com.leone.boot.common.io.array;

import com.sun.xml.internal.messaging.saaj.util.CharReader;

import java.io.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-24
 **/
public class CharArrayReaderWriter {

    /**
     * 1.使用 FileReader 读取文件到 ByteArrayReader
     * 2.从 ByteArrayWriter 读取字节到 ByteArrayReader
     * 3.从 ByteReader 读取数据到 FileWriter 保存到磁盘
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("d:/a.txt");
        CharArrayWriter charArrayWriter = new CharArrayWriter();

        int i;
        char[] chars = new char[1024];
        while ((i = reader.read(chars)) != -1) {
            charArrayWriter.write(chars, 0, i);
        }

        FileWriter writer = new FileWriter("d:/b.txt");
        CharArrayReader charArrayReader = new CharArrayReader(charArrayWriter.toCharArray());

        while ((i = charArrayReader.read(chars)) != -1) {
            writer.write(chars, 0, i);
        }

        reader.close();
        writer.close();
        charArrayReader.close();
        charArrayWriter.close();
    }

}
