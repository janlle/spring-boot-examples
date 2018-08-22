package com.andy.design.behavior.observer;

/***
 * 抽象观察者
 * <p> 定义了一个update()方法，当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调。
 *
 * @author: Leone
 * @since: 2018-08-22
 **/
public interface Observer {

    void update(String message);

}