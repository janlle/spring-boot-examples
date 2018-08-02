package com.andy.design.structure.composite;

/**
 * 组合模式
 *
 * @author: lyon
 * @since: 2018-08-01
 **/
public class Client {

    public static void main(String[] args) {
        Component root = new ConcreteCompany("root");
        root.add(new Leaf("file A"));
        root.add(new Leaf("file B"));

        Component file = new ConcreteCompany("file1");
        file.add(new Leaf("file C"));
        file.add(new Leaf("file D"));
        root.add(file);

        root.display(1);
    }

}