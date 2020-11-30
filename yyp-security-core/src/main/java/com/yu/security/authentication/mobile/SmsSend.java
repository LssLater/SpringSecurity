package com.yu.security.authentication.mobile;

/**
 * @Author: yy
 * @Date: 2020/11/30 16:04
 * @Version: 1.0.0
 */

public interface SmsSend {
    /**
     * 发送短信
     * @param mobile 手机号
     * @param content 发送内容
     * @return
     */
    boolean sendSms(String mobile, String content);
}
