package com.leone.boot.shiro.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * @author leone
 * @since 2020-05-26
 **/
@Slf4j
@RestController
@RequestMapping("/api/token")
public class TokenController {


    @PostMapping("/login")
    public String login(@RequestParam(required = false, defaultValue = "398122") String account,
                        @RequestParam(required = false, defaultValue = "459656") String password) {
        log.info("account: {} password: {}", account, password);
        return "login";
    }

}
