package com.leone.boot.common.io.print;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-24
 **/
public class PrintStreamDemo {

    public static void main(String[] args) throws FileNotFoundException {
        //PrintStream printStream = System.out;
        PrintStream printStream = new PrintStream("d:/e.txt");
        printStream.println("hello");
        printStream.println("world");

        printStream.close();
    }

}
