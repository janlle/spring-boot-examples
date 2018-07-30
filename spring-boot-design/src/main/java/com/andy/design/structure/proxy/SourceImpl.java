package com.andy.design.structure.proxy;

/**
 * @author: lyon
 * @cerateBy: 2018-07-29
 **/
public class SourceImpl implements Source {

    @Override
    public void method() {
        System.out.println("the original method!");
    }


}
