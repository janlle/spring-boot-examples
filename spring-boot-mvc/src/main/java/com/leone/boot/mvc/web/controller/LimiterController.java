package com.leone.boot.mvc.web.controller;

import com.leone.boot.mvc.limiter.SlidingWindowRateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> redisson 滑动窗口限流
 *
 * @author leone
 * @since 2024-11-15
 **/
@RestController
@RequestMapping("/limiter")
public class LimiterController {

    @Autowired
    private SlidingWindowRateLimiter slidingWindowRateLimiter;

    @GetMapping("/test")
    public Boolean limiter(@RequestParam("lock") String lock) {
        return slidingWindowRateLimiter.tryAcquire(lock, 3, 10);
    }


}
