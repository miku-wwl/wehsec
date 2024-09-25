package com.weilai.hsec.service;

import com.weilai.hsec.config.DBUserDetailsManager;
import com.weilai.hsec.entity.User;
import com.weilai.hsec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DBUserDetailsManager dbUserDetailsManager;

    @Override
    public void saveUserDetails(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()
                .username(user.getUsername()) //自定义用户名
                .password(user.getPassword()) //自定义密码
                .build();
        dbUserDetailsManager.createUser(userDetails);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
