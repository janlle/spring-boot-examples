package com.leone.boot.common.io.file;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-24
 **/
public class FileReaderWriter {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("d:/a.txt");
        FileWriter fileWriter = new FileWriter("d:/b.txt");

        int i;
        char[] ch = new char[1024];
        while ((i = fileReader.read(ch, 0, ch.length)) != -1) {
            fileWriter.write(ch, 0, i);
        }

        fileReader.close();
        fileWriter.close();
    }

}
