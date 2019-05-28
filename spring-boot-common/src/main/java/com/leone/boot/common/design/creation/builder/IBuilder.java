package com.leone.boot.common.design.creation.builder;

/**
 * @author leone
 * @since 2018-07-29
 **/
public interface IBuilder {

    void buildName();

    void buildAge();

    void buildBirthday();

    User build();

}
