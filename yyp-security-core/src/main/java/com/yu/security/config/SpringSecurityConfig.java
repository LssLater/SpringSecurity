package com.yu.security.config;

import com.yu.security.authentication.code.ImageCodeValidateFilter;
import com.yu.security.authentication.mobile.MobileAuthenticationConfig;
import com.yu.security.authentication.mobile.MobileValidateFilter;
import com.yu.security.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

/**
 * @Author: yy
 * @Date: 2020/11/29 16:03
 * @Version: 1.0.0
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserDetailsService customUserDetailsService;

    // 注入自定义的认证成功处理器
    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    ImageCodeValidateFilter imageCodeValidateFilter;

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 记住我功能
     */
    @Autowired
    DataSource dataSource;

    @Autowired
    private MobileValidateFilter mobileValidateFilter;

    @Autowired
    private MobileAuthenticationConfig mobileAuthenticationConfig;
    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 是否启动时自动创建表，第一次启动时开启即可，以后再启动需要将其注释，否则会报错
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 设置默认的加密方式
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器：
     * 1、认证信息提供方式（用户名、密码、当前用户资源权限）
     * 2、可采用内存存储方式、也可采用数据库方式
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        String password = passwordEncoder().encode("123456");
//        logger.info("加密之后存储的密码："+password);
//        auth.inMemoryAuthentication().withUser("yyp").password(password)
//                .authorities("ADMIN");
          auth.userDetailsService(customUserDetailsService);
    }

    /**
     * 资源权限配置（过滤器链）：
     * 1、被拦截的资源
     * 2、资源所对应的角色权限
     * 3、定义认证方式：httpBasic、httpForm
     * 4、定制登陆页面、登录请求地址、错误处理方式
     * 5、自定义Spring Security 过滤器
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(mobileValidateFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(imageCodeValidateFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()// 表单认证
//                .loginPage("/login/page") // 登陆页面
//                .loginProcessingUrl("/login/form") // 登陆表单提交处理URL 默认时/login
//                .usernameParameter("name")
//                .passwordParameter("pwd")
                .loginPage(securityProperties.getAuthentication().getLoginPage())
                .loginProcessingUrl(securityProperties.getAuthentication().getLoginProcessingUrl())
                .usernameParameter(securityProperties.getAuthentication().getUsernameParameter())
                .passwordParameter(securityProperties.getAuthentication().getPasswordParameter())
                .successHandler(customAuthenticationSuccessHandler) // 认证成功处理器
                .failureHandler(customAuthenticationFailureHandler) // 认证失败处理器
                .and()
                .authorizeRequests() // 认证请求
                //.antMatchers("/login/page").permitAll() // 放行跳转认证请求
                .antMatchers(securityProperties.getAuthentication().getLoginPage(),
                        securityProperties.getAuthentication().getImageCodeUrl(),
                        securityProperties.getAuthentication().getMobileCodeUrl(),
                        securityProperties.getAuthentication().getMobilePage()).permitAll()
                .anyRequest().authenticated() // 所有进入应用的HTTP请求都要进行认证
                .and()
                .rememberMe()
                .tokenRepository(jdbcTokenRepository()) // 保存登录信息
                .tokenValiditySeconds(60*60*24*7);

        http.apply(mobileAuthenticationConfig);
    }

    /**
     * 放行静态资源
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(securityProperties.getAuthentication().getStaticPaths());
    }
}
