package com.andy.aop.reflect;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-30
 **/
public class ReflectMain {

    public static void main(String[] args) throws Exception {
        Class<Student> clazz = Student.class;
//        Class<Student> clazz = (Class<Student>) new Student().getClass();
//        Class<Student> clazz = (Class<Student>) Class.forName("com.andy.aop.reflect.Student");

        System.out.println("全限定类名:" + clazz.getName());
        System.out.println("类名:" + clazz.getSimpleName());
        System.out.println("实例化:" + clazz.newInstance());




    }


}
