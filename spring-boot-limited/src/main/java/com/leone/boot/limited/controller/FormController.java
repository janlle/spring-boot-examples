package com.leone.boot.limited.controller;

import com.leone.boot.limited.anno.CacheLock;
import com.leone.boot.limited.anno.CacheParam;
import com.leone.boot.limited.anno.LocalLock;
import org.springframework.web.bind.annotation.*;

/**
 * <p> @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, isolation = Isolation.DEFAULT)
 *
 * @author leone
 * @since 2018-05-23
 **/
@RestController
@RequestMapping("/api")
public class FormController {

    @GetMapping("/user")
    @CacheLock(prefix = "books")
    @LocalLock(key = "test", expire = 6)
    public String test(@CacheParam(name = "token") @RequestParam String token) {
        return "token: " + token;
    }

    @LocalLock(key = "book")
    @PostMapping("/book")
    public String save(@RequestParam String bookName, @RequestParam Integer bookPrice) {
        return "bookName: " + bookName + " bookPrice: " + bookPrice;
    }

    @CacheLock(prefix = "user", expire = 3)
    @PostMapping("/user")
    public String save(@RequestParam String username, @RequestParam String email) {
        return "username: " + username + " email: " + email;
    }

}
