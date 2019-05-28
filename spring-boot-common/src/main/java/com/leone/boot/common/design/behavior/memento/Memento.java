package com.leone.boot.common.design.behavior.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-28
 **/
public class Memento {

    private List<String> states;
    private int index;

    /**
     * 构造函数
     */
    public Memento(List<String> states, int index) {
        this.states = new ArrayList<>(states);
        this.index = index;
    }

    public List<String> getStates() {
        return states;
    }

    public int getIndex() {
        return index;
    }

}