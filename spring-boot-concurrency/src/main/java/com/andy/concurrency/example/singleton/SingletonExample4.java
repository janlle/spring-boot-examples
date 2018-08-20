package com.andy.concurrency.example.singleton;

import com.andy.concurrency.annotations.NotRecommend;
import com.andy.concurrency.annotations.NotThreadSafe;

/**
 * 双重单例锁模式，线程不安全
 * @author: Leone
 * @since: 2018-05-06 13:19
 **/
@NotThreadSafe
@NotRecommend
public class SingletonExample4 {

    private SingletonExample4() {
    }

    private static SingletonExample4 instance = null;


    /**
     * 实例化一个对象的步骤
     * 1.memory=allocate() 分配内存空间
     * 2.初始化对象
     * 3.instance = memory 设置instance指向刚分配的内存
     */

    /**
     * JVM 和 CPU 优化，发生指令重排
     */

    /**
     * 2.初始化对象
     * 1.memory=allocate() 分配内存空间
     * 3.instance = memory 设置instance指向刚分配的内存
     */

    //静态工厂
    //双层判断加锁机制
    public static SingletonExample4 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample4.class) {
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }


}
