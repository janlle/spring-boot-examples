package com.andy.design.behavior.command;

import javax.sound.midi.Receiver;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-08-24
 **/
public class ConcreteCommand extends Command {

    //维持一个对请求接收者对象的引用
    private Receiver receiver;

    public void execute() {
//        receiver.action(); //调用请求接收者的业务处理方法action()
    }

}