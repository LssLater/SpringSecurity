package com.yu.security.authentication.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送短信验证码
 * @Author: yy
 * @Date: 2020/11/30 16:05
 * @Version: 1.0.0
 */

public class SmsCodeSender implements SmsSend {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *
     * @param mobile 手机号
     * @param content 发送内容
     * @return
     */
    @Override
    public boolean sendSms(String mobile, String content) {
        String sendContent = String.format("验证码%s,请勿泄露", content);
        // 调用第三方发送功能的SDK
        logger.info(("向手机号："+mobile+"发送的短信为："+sendContent));
        return true;
    }
}
