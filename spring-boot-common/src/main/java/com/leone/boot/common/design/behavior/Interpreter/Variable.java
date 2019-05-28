package com.leone.boot.common.design.behavior.Interpreter;

/**
 * <p>一个Variable对象代表一个有名变量
 *
 * @author leone
 * @since 2019-05-28
 **/
public class Variable extends Expression {

    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable) {
            return this.name.equals(((Variable) obj).name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean interpret(Context ctx) {
        return ctx.lookup(this);
    }

}