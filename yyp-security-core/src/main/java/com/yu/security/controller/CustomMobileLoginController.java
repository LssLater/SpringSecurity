package com.yu.security.controller;

import com.yu.base.result.MengxueguResult;
import com.yu.security.authentication.mobile.SmsSend;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yy
 * @Date: 2020/11/30 16:15
 * @Version: 1.0.0
 */
@Controller
public class CustomMobileLoginController {
    public static final String SESSION_KEY = "SESSION_KEY_MOBILE_CODE";

    /**
     * 前往手机验证码登录页
     * @return
     */
    @RequestMapping("/mobile/page")
    public String toMobile() {
        return "login-mobile";
    }

    @Autowired
    SmsSend smsSend;

    @ResponseBody
    @RequestMapping("/code/mobile")
    public MengxueguResult smsCode(HttpServletRequest request){
        // 1、随机生成一个4位数手机验证码
        String code = RandomStringUtils.randomNumeric(4);
        // 2、将手机验证码保存到session
        request.getSession().setAttribute(SESSION_KEY,code);
        // 3、发送验证码到手机上
        String mobile = request.getParameter("mobile");
        smsSend.sendSms(mobile, code);
        return MengxueguResult.ok();
    }
}
