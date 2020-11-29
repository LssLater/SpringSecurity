package com.yu.security.properties;

/**
 * @Author: yy
 * @Date: 2020/11/29 19:41
 * @Version: 1.0.0
 */
public class AuthenticationProperties {
    // 如果application.yml中没有配置相关属性、将会使用以下默认值
    private String loginPage = "/login/page";
    private String loginProcessingUrl = "/login/form";
    private String usernameParameter = "name";
    private String passwordParameter = "pwd";
    private String[] staticPaths = {"/dist/**", "/modules/**", "/plugins/**"};

    private LoginResponseType loginType = LoginResponseType.REDIRECT;

    public LoginResponseType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginResponseType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }

    public String getUsernameParameter() {
        return usernameParameter;
    }

    public void setUsernameParameter(String usernameParameter) {
        this.usernameParameter = usernameParameter;
    }

    public String getPasswordParameter() {
        return passwordParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        this.passwordParameter = passwordParameter;
    }

    public String[] getStaticPaths() {
        return staticPaths;
    }

    public void setStaticPaths(String[] staticPaths) {
        this.staticPaths = staticPaths;
    }
}
