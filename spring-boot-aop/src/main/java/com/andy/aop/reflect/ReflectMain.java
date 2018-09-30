package com.andy.aop.reflect;

import com.andy.aop.anno.NameAnnotation;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-30
 **/
@Slf4j
public class ReflectMain {

    public static void main(String[] args) throws Exception {
        Class<Student> clazz = Student.class;
        Student student = clazz.newInstance();

//        Class<Student> clazz = (Class<Student>) new Student().getClass();
//        Class<Student> clazz = (Class<Student>) Class.forName("com.andy.aop.reflect.Student");

//        System.out.println("全限定类名:" + clazz.getName());
//        System.out.println("类名:" + clazz.getSimpleName());
//        System.out.println("实例化:" + clazz.newInstance());

        // 获取单个构造方法 用于非私有的构造方法
//        Constructor<Student> constructor = clazz.getConstructor(Long.class, String.class, int.class, Date.class);
        // 获取单个构造方法 用户获取所有类型构造方法
//        Constructor<Student> constructor = clazz.getDeclaredConstructor(Long.class, String.class, int.class, Date.class);
//        Constructor.setAccessible(new AccessibleObject[]{constructor}, true);
//        System.out.println(constructor.getName());
//        System.out.println(Arrays.toString(constructor.getParameterTypes()));
//        System.out.println(constructor.getParameterCount());


        // 获得所有公共构造方法
//        Constructor<?>[] constructors = clazz.getConstructors();
        // 获得所有构造方法
//        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        // 取消安全性检查,设置后才可以使用private修饰的构造函数，也可以单独对某个构造函数进行设置
//        Constructor.setAccessible(constructors, true);

//        for (int i = 0; i < constructors.length; i++) {
//            Class<?> parameterTypesClass[] = constructors[i].getParameterTypes();
//            System.out.print("第" + i + "个构造函数:\t (");
//            for (int j = 0; j < parameterTypesClass.length; j++) {
//                System.out.print(parameterTypesClass[j].getName() + (j == parameterTypesClass.length - 1 ? "" : " ---- "));
//            }
//            System.out.println(")");
//        }

        // 获取所有属性
//        Field[] fields = clazz.getDeclaredFields();
        // 只获取共有属性
//        Field[] fields = clazz.getFields();

        // 取消安全性检查,设置后才可以获取或者修改private修饰的属性，也可以批量对所有属性进行设置
//        Field.setAccessible(fields, true);
//        System.out.println(fields.length);
//        for (int i = 0; i < fields.length; i++) {
//            // 取消安全性检查,设置后才可以获取或者修改private修饰的属性，也可以批量对所有属性进行设置
//            fields[i].setAccessible(true);
//            System.out.println("属性名:" + fields[i].getName() + "\t\t属性值:" + fields[i].get(student) + "  \t\t属性类型:" + fields[i].getType());
//        }
//
//        Field fieldUserId = clazz.getDeclaredField("userId");
//        fieldUserId.set(student, 22L);
//        System.out.println(student);

        Method[] methods = clazz.getDeclaredMethods();

//        Method[] methods = clazz.getMethods();

        Method.setAccessible(methods, true);

        for (int i = 0; i < methods.length; i++) {
            System.out.println("方法名:" + methods[i].getName() + " \t\t返回类型:" + methods[i].getReturnType().getName());
        }

        System.out.println("-------------------");
        Method defMethod = clazz.getDeclaredMethod("defMethod", String.class);
        // 取消安全性检查,设置后才可以调用private修饰的方法，也可以批量对所有方法进行设置
        defMethod.setAccessible(true);
        Object result = defMethod.invoke(student, "james");
        System.out.println(result);

        Method annotationMethod = clazz.getDeclaredMethod("priMethod");
        annotationMethod.setAccessible(true);
        NameAnnotation annotation = annotationMethod.getAnnotation(NameAnnotation.class);
        Annotation[][] parameterAnnotations = annotationMethod.getParameterAnnotations();
        System.out.println(Arrays.toString(parameterAnnotations));
        System.out.println(annotation.name());
        System.out.println(annotation.age());

    }


}
