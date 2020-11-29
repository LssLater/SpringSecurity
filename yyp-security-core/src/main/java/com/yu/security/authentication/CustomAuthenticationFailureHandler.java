package com.yu.security.authentication;

import com.yu.base.result.MengxueguResult;
import com.yu.security.properties.LoginResponseType;
import com.yu.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yy
 * @Date: 2020/11/29 22:04
 * @Version: 1.0.0
 */
@Component("customAuthenticationFailureHandler")
//public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    SecurityProperties securityProperties;

    /**
     * 认证失败处理器
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (LoginResponseType.JSON.equals(securityProperties.getAuthentication().getLoginType())) {
            MengxueguResult result = MengxueguResult.build(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(result.toJsonString());
        }else {
            super.setDefaultFailureUrl(securityProperties.getAuthentication().getLoginPage()+"?error");
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
