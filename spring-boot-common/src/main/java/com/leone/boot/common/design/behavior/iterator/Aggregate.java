package com.leone.boot.common.design.behavior.iterator;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-28
 **/
public abstract class Aggregate {

    /**
     * 工厂方法，创建相应迭代子对象的接口
     */
    public abstract Iterator createIterator();


}
