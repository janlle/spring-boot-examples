package com.andy.design.behavior.observer;

/**
 * <p> 观察者模式测试
 *
 * @author Leone
 * @since 2018-08-22
 **/
public class ObserverTest {


    public static void main(String[] args) {
        WeChatServer server = new WeChatServer();

        Observer jack = new User("jack");
        Observer andy = new User("andy");
        Observer jerry = new User("jerry");

        server.registerObserver(jack);
        server.registerObserver(andy);
        server.registerObserver(jerry);

        server.setInfomation("PHP是世界上最好用的语言！");

        System.out.println("------------------------");
        server.removeObserver(jack);
        server.setInfomation("JAVA是世界上最好用的语言！");
    }




}
