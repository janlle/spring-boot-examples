package com.andy.security.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.andy.security.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    public List<User> list() {
        List<User> users = new ArrayList<>();
        users.add(new User(1002L, "james", "james", new Date(), 1000.0, new Date(), false));
        users.add(new User(1002L, "james", "james", new Date(), 1000.0, new Date(), false));
        users.add(new User(1002L, "james", "james", new Date(), 1000.0, new Date(), false));
        users.add(new User(1002L, "james", "james", new Date(), 1000.0, new Date(), false));
        return users;
    }

    @PostMapping("/authentication/form")
    public String login() {
        return "login";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//	@PostAuthorize("hasRole('ROLE_ADMIN')")
//	@PreFilter("")
//	@PostFilter("")
    @GetMapping("/roleAuth")
    public String role() {
        return "hello role it's security!";
    }

    @PreAuthorize("#id<10 and principal.username.equals(#username) and user.username.equals('abc')")
    @PostAuthorize("returnObject%2==0")
    @RequestMapping("/test")
    public Integer test(Integer id, String username, User user) {
        return id;
    }

    @PreFilter("filterObject%2==0")
    @PostFilter("filterObject%4==0")
    @RequestMapping("/test2")
    public List<Integer> test(List<Integer> idList) {
        return idList;
    }

}
