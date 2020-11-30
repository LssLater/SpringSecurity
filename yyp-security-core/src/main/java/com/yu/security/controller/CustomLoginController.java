package com.yu.security.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: yy
 * @Date: 2020/11/29 18:51
 * @Version: 1.0.0
 */
@Controller
public class CustomLoginController {

    Logger logger = LoggerFactory.getLogger(getClass());
    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
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

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @RequestMapping("/code/image")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取验证码字符串
        String code = defaultKaptcha.createText();
        logger.info("生成的图形验证码是："+code);
        // 将字符串放到Session中
        request.getSession().setAttribute(SESSION_KEY, code);
        // 获取验证码图片
        BufferedImage image = defaultKaptcha.createImage(code);
        // 写出验证码图片
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
    }
}
