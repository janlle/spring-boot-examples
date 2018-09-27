package com.andy.cache.controller;

import com.andy.cache.anno.CacheLock;
import com.andy.cache.anno.CacheParam;
import com.andy.cache.anno.LocalLock;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-08
 **/
@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping
    @CacheLock(prefix = "books")
    public String query(@CacheParam(name = "token") @RequestParam String token) {
        return "success - " + token;
    }

    @PostMapping
    @LocalLock(key = "book:arg[0]")
    public String save(@RequestParam String token) {
        return "success - " + token;
    }


}