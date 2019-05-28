package com.leone.boot.common.design.creation.prototype;

import java.io.Serializable;

/**
 * @author leone
 * @since 2018-07-29
 **/
public class School implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
