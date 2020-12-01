package com.yu.security.properties;

import lombok.Data;

/**
 * @Author: yy
 * @Date: 2020/11/29 19:41
 * @Version: 1.0.0
 */
@Data
public class AuthenticationProperties {
    // 如果application.yml中没有配置相关属性、将会使用以下默认值
    private String loginPage = "/login/page";
    private String loginProcessingUrl = "/login/form";
    private String usernameParameter = "name";
    private String passwordParameter = "pwd";
    private String[] staticPaths = {"/dist/**", "/modules/**", "/plugins/**"};

    private LoginResponseType loginType = LoginResponseType.REDIRECT;

    private String imageCodeUrl = "/code/image";

    private String mobileCodeUrl = "/code/mobile";

    private String mobilePage = "/mobile/page";

    private Integer tokenValiditySeconds = 60*60*24*7;

}
