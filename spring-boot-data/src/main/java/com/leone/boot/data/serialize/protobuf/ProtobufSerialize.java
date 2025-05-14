package com.leone.boot.data.serialize.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

public class ProtobufSerialize {

    public static void main(String[] args) {
        testWrite();
    }

    static List<UserProto.User> userList = new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            UserProto.User user = UserProto.User.newBuilder()
              .setUsername("Alice" + i)
              .setAge(i)
              .build();
            userList.add(user);
        }
    }

    public static void testWrite() {
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            // 创建一个 Person 对象
            UserProto.UserList users = UserProto.UserList.newBuilder()
              .addAllUsers(userList)
              .build();
            // 将 Person 对象序列化为字节数组
            byte[] data = users.toByteArray();
        }
        long l2 = System.currentTimeMillis();

        System.out.println(l2 - l1);
    }

    public static void testWriteRead() {
        // 创建一个 Person 对象
        UserProto.User user = UserProto.User.newBuilder()
          .setUsername("Alice")
          .setAge(30)
          .build();

        // 将 Person 对象序列化为字节数组
        byte[] data = user.toByteArray();

        // 从字节数组反序列化为 Person 对象
        UserProto.User parsedUser = null;
        try {
            parsedUser = UserProto.User.parseFrom(data);
            // 打印解析后的 Person 对象
            System.out.println("Username: " + parsedUser.getUsername());
            System.out.println("Age: " + parsedUser.getAge());
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }


}
