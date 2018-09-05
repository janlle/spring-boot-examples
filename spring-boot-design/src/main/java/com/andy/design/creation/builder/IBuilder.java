package com.andy.design.creation.builder;

/**
 * @author Leone
 * @cerateBy: 2018-07-29
 **/
public interface IBuilder {

    void buildName();

    void buildAge();

    void buildBirthday();

    User build();

}
