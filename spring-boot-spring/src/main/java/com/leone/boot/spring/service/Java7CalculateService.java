package com.leone.boot.spring.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * <p> 基于java7实现的加法
 *
 * @author leone
 * @since 2019-05-26
 **/
@Profile("java7")
@Service
public class Java7CalculateService implements CalculateService {
    @Override
    public int calculate(Integer... values) {
        System.out.println("java7 calculate");
        int result = 0;
        for (int i = 0; i < values.length; i++) {
            result += values[i];
        }
        return result;
    }
}
