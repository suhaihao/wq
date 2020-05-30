package com.bhst.wq.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint;

    private final AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    private final AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    private final AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

    private final AjaxAccessDeniedHandler ajaxAccessDeniedHandler;

    private final SelfAuthenticationProvider selfAuthenticationProvider;


    @Autowired
    public SecurityConfig(AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint, AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler, AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler, AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler, AjaxAccessDeniedHandler ajaxAccessDeniedHandler, SelfAuthenticationProvider selfAuthenticationProvider) {
        this.ajaxAuthenticationEntryPoint = ajaxAuthenticationEntryPoint;
        this.ajaxAuthenticationSuccessHandler = ajaxAuthenticationSuccessHandler;
        this.ajaxAuthenticationFailureHandler = ajaxAuthenticationFailureHandler;
        this.ajaxLogoutSuccessHandler = ajaxLogoutSuccessHandler;
        this.ajaxAccessDeniedHandler = ajaxAccessDeniedHandler;
        this.selfAuthenticationProvider = selfAuthenticationProvider;
    }


    /**
     * 此处给AuthenticationManager添加登陆验证的逻辑。
     * 这里添加了两个AuthenticationProvider分别用于用户名密码登陆的验证以及token授权登陆两种方式。
     * 在处理登陆信息的过滤器执行的时候会调用这两个provider进行登陆验证。
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(selfAuthenticationProvider).eraseCredentials(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().cors().and()

                .httpBasic().authenticationEntryPoint(ajaxAuthenticationEntryPoint)

                .and()

                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .anyRequest()
                .authenticated()// 其他 url 需要身份认证

                .and()
                .headers().frameOptions().disable()
                .and()
                .formLogin()  //开启登录
                .successHandler(ajaxAuthenticationSuccessHandler) // 登录成功
                .failureHandler(ajaxAuthenticationFailureHandler) // 登录失败
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(ajaxLogoutSuccessHandler)
                .permitAll();

        http.exceptionHandling().accessDeniedHandler(ajaxAccessDeniedHandler); // 无权访问 JSON 格式的数据

    }
}
