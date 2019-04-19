package com.leone.concurrency.example.singleton;

import com.leone.concurrency.annotations.NotRecommend;
import com.leone.concurrency.annotations.NotThreadSafe;

/**
 * 单例懒汉模式,线程安全，性能差
 * @author Leone
 * @since 2018-05-06
 **/
@NotThreadSafe
@NotRecommend
public class SingletonExample3 {

    private SingletonExample3() {
    }

    private static SingletonExample3 instance = null;

    //静态工厂
    public static synchronized SingletonExample3 getInstance() {

        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;

    }


}
