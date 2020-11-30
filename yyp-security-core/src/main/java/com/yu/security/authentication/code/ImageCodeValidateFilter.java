package com.yu.security.authentication.code;

import com.yu.security.authentication.CustomAuthenticationFailureHandler;
import com.yu.security.authentication.exception.ValidateCodeException;
import com.yu.security.controller.CustomLoginController;
import com.yu.security.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yy
 * @Date: 2020/11/30 14:49
 * @Version: 1.0.0
 */
@Component("imageCodeValidateFilter")
public class ImageCodeValidateFilter extends OncePerRequestFilter {

    @Autowired
    SecurityProperties securityProperties;

    @Autowired
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (securityProperties.getAuthentication().getLoginProcessingUrl().equals(request.getRequestURI())&&request.getMethod().equalsIgnoreCase("post")){
            try {
                // 校验验证码合法性
                validate(request);
            }catch (AuthenticationException e){
                customAuthenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(HttpServletRequest request) throws ValidateCodeException {
        // 1、先获取session中的验证码
        String sessionCode = (String) request.getSession().getAttribute(CustomLoginController.SESSION_KEY);
        // 2、获取用户输入的验证码
        String inputCode = request.getParameter("code");
        // 3、判断是否正确
        if (StringUtils.isBlank(inputCode)){
            throw new ValidateCodeException("验证码不能为空");
        }

        if (!inputCode.equalsIgnoreCase(sessionCode)){
            throw new ValidateCodeException("验证码错误");
        }
    }
}
