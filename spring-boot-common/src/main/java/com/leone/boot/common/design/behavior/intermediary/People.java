package com.leone.boot.common.design.behavior.intermediary;

/**
 * <p> 
 *
 * @author leone
 * @since 2019-05-28
 **/
public abstract class People {

    protected Mediator mediator;

    public String name;

    // 在创建对象的时候就把他的中间者传入，他要发送都是通过这个中介者来做的。
    public People(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
    
    abstract void sendToAll(String msg);

    abstract void senToPerson(String msg, String name);

    abstract void accept(String msg);

    abstract void join();

    abstract void leave();

}
