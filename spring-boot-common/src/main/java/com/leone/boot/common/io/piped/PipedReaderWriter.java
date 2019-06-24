package com.leone.boot.common.io.piped;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-24
 **/
public class PipedReaderWriter {

    public static void main(String[] args) throws IOException {
        PipedReader pipedReader = new PipedReader();
        PipedWriter pipedWriter = new PipedWriter();

        pipedReader.connect(pipedWriter);

        pipedWriter.write(97);
        pipedWriter.write(98);
        pipedWriter.write(99);

        for (int i = 0; i < 3; i++) {
            System.out.println(pipedReader.read());
        }

        pipedReader.close();
        pipedWriter.close();
    }

}
