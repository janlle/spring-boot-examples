package com.leone.boot.common.design.behavior.iterator;

/**
 * <p> 客户端类
 *
 * @author leone
 * @since 2019-05-28
 **/
public class Client {

    public static void main(String[] args) {
        Object[] objArray = {"One", "Two", "Three", "Four", "Five", "Six"};

        //创建聚合对象
        Aggregate agg = new ConcreteAggregate(objArray);

        //循环输出聚合对象中的值
        Iterator it = agg.createIterator();
        while (!it.isDone()) {
            System.out.println(it.currentItem());
            it.next();
        }
    }

}