package com.andy.design.structure.proxy;

/**
 * @author: Leone
 * @cerateBy: 2018-07-29
 **/
public class Proxy implements Source {

    private SourceImpl source;


    public Proxy() {
        super();
        this.source = new SourceImpl();
    }


    @Override
    public void method() {
        before();
        source.method();
        after();
    }

    private void after() {
        System.out.println("after proxy!");
    }

    private void before() {
        System.out.println("before proxy!");
    }


}
