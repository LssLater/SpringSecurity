package com.yu.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: yy
 * @Date: 2020/11/29 18:51
 * @Version: 1.0.0
 */
@Controller
public class CustomLoginController {

    /**
     * 前往登陆页面
     * @param request
     * @param response
     * @return login.html
     */
    @RequestMapping("/login/page")
    public String toLogin(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }
}
