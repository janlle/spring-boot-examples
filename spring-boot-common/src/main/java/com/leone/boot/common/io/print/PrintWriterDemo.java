package com.leone.boot.common.io.print;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-24
 **/
public class PrintWriterDemo {

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("d:/e.txt");
        printWriter.println("hello");
        printWriter.println("world");
        printWriter.println("hi");

        printWriter.flush();
        printWriter.close();
    }

}
