package com.andy.shiro.common.enums;


public interface BaseEnum<E extends Enum<?>, T> {

    T code();

    String name();
}