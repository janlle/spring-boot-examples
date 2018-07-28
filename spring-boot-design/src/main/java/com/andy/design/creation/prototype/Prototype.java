package com.andy.design.creation.prototype;

/**
 * @author: lyon
 * @cerateBy: 2018-07-28
 **/
public class Prototype implements Cloneable {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Prototype prototype = (Prototype) super.clone();
        return prototype;
    }
}
