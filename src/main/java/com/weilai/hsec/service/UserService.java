package com.weilai.hsec.service;

import com.weilai.hsec.entity.User;

import java.util.List;

public interface UserService {
    void saveUserDetails(User user);

    List<User> findAll();
}