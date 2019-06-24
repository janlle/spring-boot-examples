package com.leone.boot.common.io.buffer;

import java.io.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-24
 **/
public class BufferedReaderWriter {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("d:/a.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("d:/b.txt"));

        //int i;
        //char[] chars = new char[1024];
        //while ((i = reader.read(chars)) != -1) {
        //    writer.write(chars, 0, i);
        //}

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            writer.write(line);
            writer.write("\n");
            writer.flush();
        }

        reader.close();
        writer.close();
    }

}
