package com.bhst.wq.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加入自定义的安全认证
        auth.authenticationProvider(selfAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()

                .httpBasic().authenticationEntryPoint(ajaxAuthenticationEntryPoint)

                .and()

                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .anyRequest()
                .authenticated()// 其他 url 需要身份认证

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
