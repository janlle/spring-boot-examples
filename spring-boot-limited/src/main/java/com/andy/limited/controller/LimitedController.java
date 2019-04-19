package com.andy.limited.controller;

import com.andy.limited.anno.Limit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *
 * @author leone
 * @since 2019-04-19
 **/
@RestController
@RequestMapping("/api")
public class LimitedController {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    @Limit(key = "test", period = 100, count = 10)
    @GetMapping("/limited")
    public int limited() {
        // 意味着 100S 内最多允许访问10次
        return ATOMIC_INTEGER.incrementAndGet();
    }

}
