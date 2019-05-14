package com.leone.boot.design.creation.builder;

/**
 * @author leone
 * @cerateBy: 2018-07-29
 **/
public interface IBuilder {

    void buildName();

    void buildAge();

    void buildBirthday();

    User build();

}
