package com.weilai.hsec.controller;

import com.weilai.hsec.entity.User;
import com.weilai.hsec.repository.UserRepository;
import com.weilai.hsec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userRepository;

    //用户必须有 ADMIN 角色 并且 用户名是 admin 才能访问此方法
    @PreAuthorize("hasRole('ADMIN') and authentication.name == 'admin'")
    @GetMapping("/list")
    public List<User> getList() {
        return userRepository.findAll();
    }

    //用户必须有 USER_ADD 权限 才能访问此方法
    @PreAuthorize("hasAuthority('USER_ADD')")
    @PostMapping("/add")
    public void add(@RequestBody User user) {
        userService.saveUserDetails(user);
    }
}