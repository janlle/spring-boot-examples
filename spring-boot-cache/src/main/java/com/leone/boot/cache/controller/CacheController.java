package com.leone.boot.cache.controller;

import com.leone.boot.cache.anno.CacheLock;
import com.leone.boot.cache.anno.CacheParam;
import com.leone.boot.cache.anno.LocalLock;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * @author leone
 * @since 2018-09-08
 **/
@RestController
@RequestMapping("/api/cache")
public class CacheController {

    @GetMapping
    @CacheLock(prefix = "books")
    public String query(@CacheParam(name = "token") @RequestParam String token) {
        return "success - " + token;
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true,isolation = Isolation.DEFAULT)
    @PostMapping
    @LocalLock(key = "book.save()")
    public String save(@RequestParam String token) {
        return "success - " + token;
    }


}