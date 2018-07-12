package com.andy.model;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-07 17:53
 **/
public class Course extends NamedEntity {

    public Course(String id, String name) {
        super(id, name);
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
