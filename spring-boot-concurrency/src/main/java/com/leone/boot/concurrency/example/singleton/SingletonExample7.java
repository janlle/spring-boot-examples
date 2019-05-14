package com.leone.boot.concurrency.example.singleton;

import com.leone.boot.concurrency.annotations.Recommend;
import com.leone.boot.concurrency.annotations.ThreadSafe;

/**
 * 枚举模式
 * @author leone
 * @since 2018-05-06
 **/
@ThreadSafe
@Recommend
public class SingletonExample7 {

    private SingletonExample7() { }

    public static void main(String[] args) {
        Singleton.INSTANCE.getSingleton();
        System.out.println(Singleton.INSTANCE.getSingleton());
        System.out.println(Singleton.INSTANCE.getSingleton());
    }

    private enum Singleton {

        INSTANCE;

        private SingletonExample7 singleton;

        //jvm保证这个函数只被调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getSingleton() {
            return singleton;
        }
    }


}
