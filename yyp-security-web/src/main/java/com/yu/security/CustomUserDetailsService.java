package com.yu.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: yy
 * @Date: 2020/11/29 19:54
 * @Version: 1.0.0
 */
@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("请求认证的用户名："+username);
        if (!"yyp".equalsIgnoreCase(username)){
            throw new UsernameNotFoundException("用户名火密码错误");
        }
        String password = passwordEncoder.encode("1234");

        return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));

    }
}
