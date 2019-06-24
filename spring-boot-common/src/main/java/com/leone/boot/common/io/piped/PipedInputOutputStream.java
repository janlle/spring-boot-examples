package com.leone.boot.common.io.piped;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-24
 **/
public class PipedInputOutputStream {

    public static void main(String[] args) throws IOException {
        byte[] b = {'h', 'e', 'l', 'l', 'o', 'w', 'o', 'r', 'l', 'd'};

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();
        in.connect(out);

        out.write(b[0]);
        out.write(b[1]);
        out.write(b[2]);

        out.flush();

        for (int i = 0; i < 3; i++) {
            System.out.println((char) in.read());
        }

        in.close();
        out.close();
    }

}
