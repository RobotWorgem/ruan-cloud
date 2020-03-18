package com.ruan.service.controller;

import com.ruan.service.pojo.User;
import com.ruan.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        if (id == 2) {
            throw new RuntimeException("忙忙忙");
        }
        return this.userService.queryById(id);
    }
}
