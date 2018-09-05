package com.andy.concurrency.example.singleton;

import com.andy.concurrency.annotations.NotThreadSafe;

/**
 * 单例懒汉模式
 * @author Leone
 * @since: 2018-05-06 13:19
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
