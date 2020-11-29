package com.yu.security.properties;

import com.alibaba.fastjson.JSON;

/**
 * @Author: yy
 * @Date: 2020/11/29 23:41
 * @Version: 1.0.0
 */
public enum LoginResponseType {
    /**
     * 响应JSON字符串
     */
    JSON,

    /**
     * 重定向
     */
    REDIRECT
}
