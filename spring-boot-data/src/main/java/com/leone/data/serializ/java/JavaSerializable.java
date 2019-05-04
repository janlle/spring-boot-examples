package com.leone.data.serializ.java;

import com.leone.common.utils.EntityFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * <p> java serializable 序列化对象到磁盘，反序列化磁盘文件
 *
 * @author leone
 * @since 2018-05-11
 **/
@Slf4j
public class JavaSerializable {

    public static void main(String[] args) throws Exception {
        long writeTime = writeObject(1000000);
        System.out.println("writeTime: " + writeTime);

        long readTime = readObject();
        System.out.println("readTime: " + readTime);
    }

    /**
     * @param count
     * @return
     * @throws Exception
     */
    public static long writeObject(int count) throws Exception {
        long start = System.currentTimeMillis();
        File obj = new File("D:\\tmp\\user.obj");
        OutputStream outputStream = new FileOutputStream(obj);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(EntityFactory.getUsers(count));
        objectOutputStream.close();
        return System.currentTimeMillis() - start;
    }

    /**
     * @return
     * @throws Exception
     */
    public static long readObject() throws Exception {
        long start = System.currentTimeMillis();
        File obj = new File("D:\\tmp\\user.obj");
        InputStream inputStream = new FileInputStream(obj);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        return System.currentTimeMillis() - start;
    }


}
