package com.leone.boot.spring.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * <p>基于Java8 lambda实现的加法
 *
 * @author leone
 * @since 2019-05-26
 **/
@Profile("java8")
@Service
public class Java8CalculateService implements CalculateService {
    @Override
    public int calculate(Integer... values) {
        System.out.println("java8 calculate");
        return Stream.of(values).reduce(Integer::sum).get();
    }
}
