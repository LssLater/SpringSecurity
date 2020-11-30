package com.yu.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Author: yy
 * @Date: 2020/11/30 23:12
 * @Version: 1.0.0
 */
@Component("mobileUserDetailsService")
public class MobileUserDetailsService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        logger.info("请求的手机号是："+mobile);

        return new User(mobile, "", true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
    }
}
