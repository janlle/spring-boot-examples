package com.leone.boot.common.design.behavior.Interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 环境(Context)类定义出从变量到布尔值的一个映射
 *
 * @author leone
 * @since 2019-05-28
 **/
public class Context {

    private Map<Variable, Boolean> map = new HashMap<>();

    public void assign(Variable var, boolean value) {
        map.put(var, value);
    }

    public boolean lookup(Variable var) throws IllegalArgumentException {
        Boolean value = map.get(var);
        if (value == null) {
            throw new IllegalArgumentException();
        }
        return value;
    }
}