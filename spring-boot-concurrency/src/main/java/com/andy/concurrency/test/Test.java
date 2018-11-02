package com.andy.concurrency.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-30
 **/
public class Test {

    public static void main(String[] args) {

        int[] arrays = {23, 34, 12, 21};

        for (int i = 0; i < arrays.length; i++) {
//            System.out.println(arrays[i]);
        }

        int sum = IntStream.of(arrays).map(e -> e * 2).sum();
//        System.out.println(sum);
        List<String> list = new ArrayList<>();
        list.stream();
        list.parallelStream();

        Arrays.stream(new int[]{12, 23, 34});

        IntStream.range(1, 10);
        LongStream.of(12, 23, 34);

        new Random().ints().limit(2);

        Stream.generate(() -> new Random().nextInt(19)).limit(20);

        String s = "hello world my name is 007";

        Stream.of(s.split(" ")).filter(a -> a.length() > 2).map(e -> e.length()).forEach(System.out::println);


    }

}
