package com.andy.security.web.controller;

import com.andy.security.entity.User;
import com.andy.security.service.UserService;
import com.andy.security.util.EntityFactory;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return EntityFactory.getUsers(10);
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
