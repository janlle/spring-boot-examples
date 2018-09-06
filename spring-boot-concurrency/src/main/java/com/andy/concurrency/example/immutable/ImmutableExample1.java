package com.andy.concurrency.example.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leone
 * @since 2018-05-06 14:09
 **/
@Slf4j
public class ImmutableExample1 {

    private final static Integer a = 1;

    private final static String b = "2";

    private final static Map<Integer, Integer> map = new HashMap<>();


    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        //final 修饰基本类型值不可改变
//        a = 2;
//        b = "4";

        HashMap<Integer, Integer> newMap = Maps.newHashMap();

        //修饰引用类型对象指针不可改变
//        map = newMap;

        map.put(1, 7);
        log.info("{}", map.get(1));

    }

    public void test(final int i) {
//        i = 2;
    }


}
