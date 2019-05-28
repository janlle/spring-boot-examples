package com.leone.boot.common.design.behavior.intermediary;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-28
 **/
public class Main {

    public static void main(String[] args) {
        Mediator chatPlatform = new ChatPlatform();
        NormalPeople a = new NormalPeople("A", chatPlatform);
        NormalPeople b = new NormalPeople("B", chatPlatform);
        NormalPeople c = new NormalPeople("C", chatPlatform);
        a.join();
        b.join();
        c.join();
        System.out.println("-----------------A群发送消息------------------");
        a.sendToAll("A：大家听得到吗?");
        System.out.println("-----------------A给B私发消息------------------");
        a.senToPerson("A：B,我只想和你说", "B");
        System.out.println("-----------------B给A私发消息------------------");
        b.senToPerson("B:可以，请说", "A");
        System.out.println("-----------------A离开聊天室------------------");
        a.leave();
        System.out.println("-----------------B群发送消息------------------");
        b.sendToAll("B:A能听到吗");
    }

}
