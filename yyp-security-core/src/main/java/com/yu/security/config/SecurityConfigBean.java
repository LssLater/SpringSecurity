package com.yu.security.config;

import com.yu.security.authentication.mobile.SmsCodeSender;
import com.yu.security.authentication.mobile.SmsSend;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 为容器中添加Bean实例
 * @Author: yy
 * @Date: 2020/11/30 16:11
 * @Version: 1.0.0
 */
@Configuration
public class SecurityConfigBean {

    @Bean
    @ConditionalOnMissingBean(SmsSend.class)
    public SmsSend smsSend() {
        return new SmsCodeSender();
    }
}
