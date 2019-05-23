package com.leone.boot.limited.controller;

import com.leone.boot.limited.anno.CacheLock;
import com.leone.boot.limited.anno.CacheParam;
import com.leone.boot.limited.anno.LocalLock;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-23
 **/
@RestController
@RequestMapping("/api")
public class FormController {

    @GetMapping
    @CacheLock(prefix = "books")
    public String query(@CacheParam(name = "token") @RequestParam String token) {
        return "success - " + token;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, isolation = Isolation.DEFAULT)
    @PostMapping
    @LocalLock(key = "book.save()")
    public String save(@RequestParam String token) {
        return "success - " + token;
    }

}
