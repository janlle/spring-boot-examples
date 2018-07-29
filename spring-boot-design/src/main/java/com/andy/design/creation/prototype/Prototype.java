package com.andy.design.creation.prototype;

/**
 * @author: lyon
 * @cerateBy: 2018-07-28
 **/
public class Prototype implements Cloneable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally");
            return null;
        }
    }

    public static void main(String[] args) {
        Prototype pro = new Prototype();
        System.out.println(pro.getName());
        Prototype pro1 = (Prototype) pro.clone();
        System.out.println(pro1.getName());
    }

}

