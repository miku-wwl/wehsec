package com.weilai.hsec.config;

import com.weilai.hsec.config.securityHandler.MyAuthenticationEntryPoint;
import com.weilai.hsec.config.securityHandler.MyAuthenticationFailureHandler;
import com.weilai.hsec.config.securityHandler.MyAuthenticationSuccessHandler;
import com.weilai.hsec.config.securityHandler.MyLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
                .formLogin(form -> {
                    form
                            .loginPage("/login").permitAll() //登录页面无需授权即可访问
                            .usernameParameter("username") //自定义表单用户名参数，默认是username
                            .passwordParameter("password") //自定义表单密码参数，默认是password
                            .failureUrl("/login?error"); //登录失败的返回地址
                    form.successHandler(new MyAuthenticationSuccessHandler()); //认证成功时的处理
                    form.failureHandler(new MyAuthenticationFailureHandler()); //认证失败时的处理
                }); //使用表单授权方式
        http.csrf(AbstractHttpConfigurer::disable);
        http.logout(logout -> {
            logout.logoutSuccessHandler(new MyLogoutSuccessHandler()); //注销成功时的处理
        });
        //错误处理
        http.exceptionHandling(exception -> {
            exception.authenticationEntryPoint(new MyAuthenticationEntryPoint());//请求未认证的接口
        });
        //跨域
        http.cors(withDefaults());
        return http.build();
    }
}