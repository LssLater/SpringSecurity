package com.yu.security.authentication;

import com.alibaba.fastjson.JSON;
import com.yu.base.result.MengxueguResult;
import com.yu.security.properties.LoginResponseType;
import com.yu.security.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 * @Author: yy
 * @Date: 2020/11/29 21:58
 * @Version: 1.0.0
 */
@Component("customAuthenticationSuccessHandler")
//public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SecurityProperties securityProperties;

    /**
     * 认证成功后处理逻辑
     * @param authentication
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
       if (LoginResponseType.JSON.equals(securityProperties.getAuthentication().getLoginType())) {
           // 认证成功后，响应JSON数据给前端
           MengxueguResult result = MengxueguResult.ok("认证成功");
           response.setContentType("application/json;charset=UTF-8");
           response.getWriter().write(result.toJsonString());
       }else {
           // 重定向到上次请求的地址，引发跳转到认证页面的地址
           logger.info("authentication:"+ JSON.toJSONString(authentication));
           super.onAuthenticationSuccess(request, response, authentication);
       }
    }
}
