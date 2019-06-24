package com.leone.boot.common.io.data;

import java.io.*;
import java.util.Arrays;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-24
 **/
public class DataInputOutputStream {

    public static void main(String[] args) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("d:/c.txt"));
        dataOutputStream.writeByte(2);
        dataOutputStream.writeShort(3);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeLong(4L);
        dataOutputStream.writeBoolean(true);
        dataOutputStream.writeUTF("hello");
        dataOutputStream.writeChar('a');

        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("d:/c.txt"));
        byte b = dataInputStream.readByte();
        short s = dataInputStream.readShort();
        int i = dataInputStream.readInt();
        long l = dataInputStream.readLong();
        boolean bool = dataInputStream.readBoolean();
        String str = dataInputStream.readUTF();
        char c = dataInputStream.readChar();

        System.out.println(Arrays.toString(new Object[]{b, s, i, l, bool, str, c}));
        dataInputStream.close();
        dataOutputStream.close();
    }
}
