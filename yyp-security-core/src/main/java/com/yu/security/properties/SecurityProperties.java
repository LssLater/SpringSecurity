package com.yu.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: yy
 * @Date: 2020/11/29 19:39
 * @Version: 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "yyp.security")
public class SecurityProperties {
    private AuthenticationProperties authentication;

    public AuthenticationProperties getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationProperties authentication) {
        this.authentication = authentication;
    }
}
