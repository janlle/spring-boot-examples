package com.andy.design.behavior.command;

/**
 * <p> FunctionButton：功能键类，请求发送者
 *
 * @author Leone
 * @since: 2018-08-23
 **/
public class FunctionButton {

    //功能键名称
    private String name;
    //维持一个抽象命令对象的引用
    private Command command;

    public FunctionButton(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    //为功能键注入命令
    public void setCommand(Command command) {
        this.command = command;
    }

    //发送请求的方法
    public void onClick() {
        System.out.print("点击功能键：");
        command.execute();
    }
}