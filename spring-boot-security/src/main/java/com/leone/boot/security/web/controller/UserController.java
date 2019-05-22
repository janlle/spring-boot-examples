package com.leone.boot.security.web.controller;

import com.leone.boot.security.entity.User;
import com.leone.boot.security.service.UserServiceImpl;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2018-05-22
 **/
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/{account}")
    public User findByAccount(@PathVariable String account) {
        return userService.findByAccount(account);
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

    @GetMapping("/login-test")
    public boolean login(String account, String password) {
        return userService.login(account, password);
    }

}
