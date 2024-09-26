package com.weilai.hsec.config;

import com.weilai.hsec.entity.User;
import com.weilai.hsec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
//            Collection<GrantedAuthority> authorities = new ArrayList<>();
//
//            authorities.add(() -> "USER_LIST");
//            authorities.add(() -> "USER_ADD");

            /*authorities.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "USER_LIST";
                }
            });
            authorities.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "USER_ADD";
                }
            });*/

//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.getEnabled(),
//                    true, //用户账号是否过期
//                    true, //用户凭证是否过期
//                    true, //用户是否未被锁定
//                    authorities); //权限列表
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .credentialsExpired(false)
                    .accountLocked(false)
                    .roles("ADMIN")
//                    .authorities("USER_ADD", "USER_UPDATE")  roles 和 authorities 不能同时使用
                    .build();
        }
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}