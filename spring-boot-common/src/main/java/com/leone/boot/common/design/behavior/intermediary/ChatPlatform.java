package com.leone.boot.common.design.behavior.intermediary;

/**
 * <p> 聊天平台实现
 *
 * @author leone
 * @since 2019-05-28
 **/
public class ChatPlatform extends Mediator {

    @Override
    void sendToAll(String msg) {
        for (People u : list) {
            u.accept(msg);
        }
    }

    @Override
    void senToPerson(String msg, String name) {
        for (People u : list) {
            if (u.name.equals(name)) {
                u.accept(msg);
            }
        }
    }

    @Override
    void join(People people) {
        list.add(people);
    }

    @Override
    void leave(People people) {
        list.remove(people);
    }
}
