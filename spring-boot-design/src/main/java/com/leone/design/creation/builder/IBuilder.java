package com.leone.design.creation.builder;

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
