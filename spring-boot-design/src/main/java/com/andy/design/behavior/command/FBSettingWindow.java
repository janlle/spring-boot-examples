package com.andy.design.behavior.command;

import java.util.ArrayList;

/**
 * <p> 功能键设置窗口类
 *
 * @author Leone
 * @since: 2018-08-23
 **/
public class FBSettingWindow {

    //窗口标题
    private String title;

    //定义一个ArrayList来存储所有功能键
    private ArrayList<FunctionButton> functionButtons = new ArrayList<FunctionButton>();

    public FBSettingWindow(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void addFunctionButton(FunctionButton fb) {
        functionButtons.add(fb);
    }

    public void removeFunctionButton(FunctionButton fb) {
        functionButtons.remove(fb);
    }

    //显示窗口及功能键  
    public void display() {
        System.out.println("显示窗口：" + this.title);
        System.out.println("显示功能键：");
        for (Object obj : functionButtons) {
            System.out.println(((FunctionButton) obj).getName());
        }
        System.out.println("------------------------------");
    }
}


//帮助命令类：具体命令类  
class HelpCommand extends Command {
    private HelpHandler hhObj; //维持对请求接收者的引用  

    public HelpCommand() {
        hhObj = new HelpHandler();
    }

    //命令执行方法，将调用请求接收者的业务方法  
    public void execute() {
        hhObj.display();
    }
}

//最小化命令类：具体命令类  
class MinimizeCommand extends Command {
    private WindowHanlder whObj; //维持对请求接收者的引用  

    public MinimizeCommand() {
        whObj = new WindowHanlder();
    }

    //命令执行方法，将调用请求接收者的业务方法
    public void execute() {
        whObj.minimize();
    }
}

//窗口处理类：请求接收者  
class WindowHanlder {
    public void minimize() {
        System.out.println("将窗口最小化至托盘！");
    }
}

//帮助文档处理类：请求接收者  
class HelpHandler {
    public void display() {
        System.out.println("显示帮助文档！");
    }
}  