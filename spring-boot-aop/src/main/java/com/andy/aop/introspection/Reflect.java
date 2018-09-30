package com.andy.aop.introspection;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-30
 **/
public class Reflect {

    public static void main(String[] args) throws Exception {
        Point point = new Point(2, 5);
        String proName = "x";
        getProperty(point, proName);
        setProperty(point, proName);
    }

    private static void getProperty(Point point, String proName) throws Exception {
        PropertyDescriptor proDescriptor = new PropertyDescriptor(proName, Point.class);
        Method methodGetX = proDescriptor.getReadMethod();
        Object objx = methodGetX.invoke(point);
        System.out.println(objx);// 2
    }

    private static void setProperty(Point point, String proName) throws Exception {
        PropertyDescriptor proDescriptor = new PropertyDescriptor(proName, Point.class);
        Method methodSetX = proDescriptor.getWriteMethod();
        methodSetX.invoke(point, 8);
        System.out.println(point.getX());// 8
    }


}