package com.leone.boot.common.design.behavior.observer;

/**
 * <p> 观察者模式测试
 *
 * @author leone
 * @since 2018-08-22
 **/
public class ObserverTest {

    public static void main(String[] args) {
        InformationServer server = new InformationServer();

        Observer jack = new User("jack");
        Observer andy = new User("andy");
        Observer jerry = new User("jerry");

        server.registerObserver(jack);
        server.registerObserver(andy);
        server.registerObserver(jerry);

        server.setInformation("PHP 是世界上最好用的语言！");
        System.out.println("------------------------");
        server.removeObserver(jack);
        server.setInformation("JAVA 是世界上最好用的语言！");
    }
}
