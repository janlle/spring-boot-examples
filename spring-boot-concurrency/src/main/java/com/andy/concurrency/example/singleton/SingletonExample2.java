package com.andy.concurrency.example.singleton;

import com.andy.concurrency.annotations.ThreadSafe;

/**
 * 单例饿汉模式
 * @author Leone
 * @since: 2018-05-06 13:19
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
