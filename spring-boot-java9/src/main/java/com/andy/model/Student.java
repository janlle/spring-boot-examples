package com.andy.model;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-07 17:51
 **/
public class Student extends NamedEntity {

    private String group;

    public Student(String id, String name, String group) {
        super(id, name);
        this.group = group;
    }

    public Student() {
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "group='" + group + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
