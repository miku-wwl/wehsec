package com.weilai.hsec.service;

import com.weilai.hsec.entity.User;
import com.weilai.hsec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUserDetails(User user) {
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
