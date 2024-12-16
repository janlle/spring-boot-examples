package com.leone.boot.security.web.controller;

import com.leone.boot.security.entity.User;
import com.leone.boot.security.service.IUserService;
import jakarta.annotation.Resource;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2018-05-22
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    //@Resource
    private IUserService userService;

    @GetMapping
    public User findByAccount(@RequestParam String account) {
        return userService.findByAccount(account);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
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
    @RequestMapping("/filter")
    public List<Integer> test(List<Integer> idList) {
        return idList;
    }

    /*
     * 拒绝所有请求
     */
    @DenyAll
    @GetMapping("/save")
    public String save() {
        return "save";
    }

    @RolesAllowed({"user", "admin"})
    @GetMapping("/{userId}")
    public String user(@PathVariable("userId") Long userId) {
        return "find";
    }

    @GetMapping("/update")
    public String update() {
        return "update";
    }

    @GetMapping("/delete")
    public String delete() {
        return "delete";
    }

}
