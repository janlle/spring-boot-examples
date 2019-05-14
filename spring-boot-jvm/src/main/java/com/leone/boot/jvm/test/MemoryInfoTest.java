package com.leone.boot.jvm.test;

/**
 * <p> 测试jvm内存参数
 *
 * @author leone
 * @since 2019-02-03
 **/
public class MemoryInfoTest {

    public static void main(String[] args) {

        long maxMemory = Runtime.getRuntime().maxMemory();

        long totalMemory = Runtime.getRuntime().totalMemory();

        long freeMemory = Runtime.getRuntime().freeMemory();

        // 根据Xmx参数决定
        System.out.println("最大JVM可用内存: " + maxMemory / 1024 + " 字节 " + maxMemory / 1024 / 1024 + " MB");

        // 如果Xms参数则使用xms的值
        System.out.println("当前占用的内存: " + totalMemory / 1024 + " 字节 " + totalMemory / 1024 / 1024 + " MB");

        //
        System.out.println("当前jvm空闲内存: " + freeMemory / 1024 + " 字节 " + freeMemory / 1024 / 1024 + " MB");
        String str = "hello world";

//        while (true) {
//            str += new Random().nextInt(111111111) + new Random().nextInt(111111111);
//            byte[] b = new byte[1024 * 1024];
//        }
    }

}
