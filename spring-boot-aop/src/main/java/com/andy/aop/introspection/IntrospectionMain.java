package com.andy.aop.introspection;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-30
 **/
public class IntrospectionMain {

    public static void main(String[] args) throws Exception {
        Point point = new Point(2, 5);
        String proName = "x";
        System.out.println(point);
        getProperty(point, proName);
        setProperty(point, proName);


        // 获取 javaBean 实体详细信息
        BeanInfo beanInfo = Introspector.getBeanInfo(Point.class);
        // 获取 JavaBean 属性的实例对象
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        System.out.println("propertyDescriptors.length:" + propertyDescriptors.length);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            System.out.println("getName" + propertyDescriptor.getName());  //获取实体的对象名
            Method method = propertyDescriptor.getReadMethod();
            System.out.println("invoke" + method.invoke(point));
        }

    }

    private static void getProperty(Point point, String proName) throws Exception {
        PropertyDescriptor proDescriptor = new PropertyDescriptor(proName, Point.class);
        Method methodGetX = proDescriptor.getReadMethod();
        Object obj = methodGetX.invoke(point);
        System.out.println(obj);            // 2
    }

    private static void setProperty(Point point, String proName) throws Exception {
        PropertyDescriptor proDescriptor = new PropertyDescriptor(proName, Point.class);
        Method methodSetX = proDescriptor.getWriteMethod();
        methodSetX.invoke(point, 8);
        System.out.println(point.getX());   // 8
    }


}