package com.yu.security.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author: yy
 * @Date: 2020/11/30 22:43
 * @Version: 1.0.0
 */
public class MobileAuthenticationProvider implements AuthenticationProvider {

    UserDetailsService userDetailsService;

    /**
     * 处理认证
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;
        // 获取用户输入的手机号
        String mobile = (String) mobileAuthenticationToken.getPrincipal();
        // 查询数据库
        UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);
        if (userDetails == null){
            throw new AuthenticationServiceException("该用户未注册");
        }

        // 查询到用户信息，认证通过，重新构建token
        MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(userDetails,userDetails.getAuthorities());
        ((MobileAuthenticationToken) authentication).setDetails(mobileAuthenticationToken.getDetails());
        return authenticationToken;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
