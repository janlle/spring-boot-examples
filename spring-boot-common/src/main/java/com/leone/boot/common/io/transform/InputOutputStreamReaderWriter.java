package com.leone.boot.common.io.transform;

import java.io.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-24
 **/
public class InputOutputStreamReaderWriter {

    /**
     * 转换流用来转换 reader 流和 stream 流
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("d:/a.txt")));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:/b.txt")));

        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.write("\n");
            writer.flush();
        }
        reader.close();
        writer.close();
    }

}
