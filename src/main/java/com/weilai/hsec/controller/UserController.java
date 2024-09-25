package com.weilai.hsec.controller;

import com.weilai.hsec.entity.User;
import com.weilai.hsec.repository.UserRepository;
import com.weilai.hsec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/list")
    public List<User> getList() {
        return userRepository.findAll();
    }

    @PostMapping("/add")
    public void add(@RequestBody User user) {
        userService.saveUserDetails(user);
    }
}