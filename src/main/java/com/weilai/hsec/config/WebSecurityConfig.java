package com.weilai.hsec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser( //此行设置断点可以查看创建的user对象
//                User
//                        .withDefaultPasswordEncoder()
//                        .username("huan") //自定义用户名
//                        .password("password") //自定义密码
//                        .roles("USER") //自定义角色
//                        .build()
//        );
//        return manager;
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //authorizeRequests()：开启授权保护
        //anyRequest()：对所有请求开启授权保护
        //authenticated()：已认证请求会自动被授权
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .formLogin(withDefaults());//表单授权方式
//                .httpBasic(withDefaults());//基本授权方式

        return http.build();
    }
}