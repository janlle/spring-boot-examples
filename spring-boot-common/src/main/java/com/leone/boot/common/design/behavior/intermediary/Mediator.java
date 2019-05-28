package com.leone.boot.common.design.behavior.intermediary;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 中介抽象 这用聊天室代替
 *
 * @author leone
 * @since 2019-05-28
 **/
public abstract class Mediator {

    // 所有在聊天室里的人存这
    List<People> list = new ArrayList<>();

    /**
     * 群发
     *
     * @param msg
     */
    abstract void sendToAll(String msg);

    /**
     * 给某个人发送消息
     *
     * @param msg
     * @param name
     */
    abstract void senToPerson(String msg, String name);

    /**
     * 用户加入聊天室
     *
     * @param people
     */
    abstract void join(People people);

    /**
     * 用户离开聊天室
     *
     * @param people
     */
    abstract void leave(People people);
}
