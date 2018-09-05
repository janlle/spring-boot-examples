package com.andy.concurrency.example.immutable;

import com.andy.concurrency.annotations.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Leone
 * @since: 2018-05-06 14:09
 **/
@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> newMap = Maps.newHashMap();

        map.put(1, 7);
        log.info("{}", map.get(1));

    }

}
