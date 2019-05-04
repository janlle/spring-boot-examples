package com.leone.concurrency.example.singleton;

import com.leone.concurrency.annotations.ThreadSafe;

/**
 * 单例饿汉模式
 * @author leone
 * @since 2018-05-06
 **/
@ThreadSafe
public class SingletonExample2 {

    private SingletonExample2() { }

    private static SingletonExample2 instance = new SingletonExample2();

    //静态工厂
    public static SingletonExample2 getInstance() {
        return instance;
    }


}
