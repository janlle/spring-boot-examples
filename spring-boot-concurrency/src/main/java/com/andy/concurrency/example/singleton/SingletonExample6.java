package com.andy.concurrency.example.singleton;

import com.andy.concurrency.annotations.ThreadSafe;

/**
 * 单例饿汉模式
 * @author: lyon
 * @since: 2018-05-06 13:19
 **/
@ThreadSafe
public class SingletonExample6 {

    private SingletonExample6() { }

    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    //静态工厂
    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(SingletonExample6.getInstance().hashCode());
        System.out.println(SingletonExample6.getInstance().hashCode());
    }

}
