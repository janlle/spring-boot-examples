package com.leone.boot.concurrency.example.singleton;

import com.leone.boot.concurrency.annotations.NotThreadSafe;

/**
 * 单例懒汉模式
 * @author leone
 * @since 2018-05-06
 **/
@NotThreadSafe
public class SingletonExample1 {

    private SingletonExample1() {
    }

    private static SingletonExample1 instance = null;

    //静态工厂
    public static SingletonExample1 getInstance() {

        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;

    }


}
