package com.leone.boot.common.io.object;

import java.io.*;
import java.util.Date;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-24
 **/
public class ObjectInputOutputStream {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("d:/d.obj"));
        outputStream.writeObject(new User("james", 31, new Date()));

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("d:/d.obj"));
        User object = (User) inputStream.readObject();

        System.out.println(object);

        outputStream.close();
        inputStream.close();
    }

    /**
     * 想实现对象写入到磁盘必须实现 Serializable 接口，否则会报错 java.io.NotSerializableException
     */
    static class User implements Serializable {
        String name;

        int age;

        Date birthday;

        public User() {
        }

        public User(String name, int age, Date birthday) {
            this.name = name;
            this.age = age;
            this.birthday = birthday;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("User{");
            sb.append("name='").append(name).append('\'');
            sb.append(", age=").append(age);
            sb.append(", birthday=").append(birthday);
            sb.append('}');
            return sb.toString();
        }
    }

}
