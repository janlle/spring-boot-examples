package com.andy.data.serializ;

import com.andy.data.entity.User;
import com.andy.data.service.DataService;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * java serializable 序列化对象到磁盘，反序列化磁盘文件
 * @author: Mr.ruoLin
 * @createBy: 2018-05-11 22:32
 **/
@Slf4j
public class SerializEntity {

    private static User user = new User(1, "james" + 1, "admin" + 1, new Date(), 10000 + 0.1, new Date(), false);

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        writeObject();
//        User user = (User)readObject();
//        List<User> users = (List<User>)readObject();
//        log.info("user:{}", users.size());

//        for (int i = 0; i < 10000; i++) {
//            if (i % 1000 == 0) {
//                System.out.println(users.get(i));
//            }
//        }
        long end = System.currentTimeMillis();
        System.out.println("一共用了:" + (end - start) + "豪秒！");
    }


    public static void writeObject() throws Exception {
        File obj = new File("D:\\tmp\\user.obj");
        OutputStream outputStream = new FileOutputStream(obj);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(DataService.getUsers());
        objectOutputStream.close();
    }

    public static Object readObject() throws Exception {
        File obj = new File("D:\\tmp\\user.obj");
        InputStream inputStream = new FileInputStream(obj);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        return object;
    }


}
