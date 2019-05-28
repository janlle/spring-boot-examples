package com.leone.boot.common.design.behavior.intermediary;

/**
 * <p> 普通用户
 *
 * @author leone
 * @since 2019-05-28
 **/
public class NormalPeople extends People {

    public NormalPeople(String name, Mediator mediator) {
        super(name, mediator);
    }

    @Override
    void sendToAll(String msg) {
        mediator.sendToAll(msg);
    }

    @Override
    void senToPerson(String msg, String name) {
        mediator.senToPerson(msg, name);
    }

    @Override
    void accept(String msg) {
        System.out.println(this.name + "收到消息: " + msg);
    }

    @Override
    void join() {
        mediator.join(this);
    }

    @Override
    void leave() {
        mediator.leave(this);
    }
}
