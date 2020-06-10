package com.bhst.wq.config.security;

import com.bhst.wq.config.security.token.LindTokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint;

    private final AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    private final AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    private final AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

    private final AjaxAccessDeniedHandler ajaxAccessDeniedHandler;

    private final SelfAuthenticationProvider selfAuthenticationProvider;

    private final LindTokenAuthenticationFilter lindTokenAuthenticationFilter;


    @Autowired
    public SecurityConfig(AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint, AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler, AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler, AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler, AjaxAccessDeniedHandler ajaxAccessDeniedHandler, SelfAuthenticationProvider selfAuthenticationProvider, LindTokenAuthenticationFilter lindTokenAuthenticationFilter) {
        this.ajaxAuthenticationEntryPoint = ajaxAuthenticationEntryPoint;
        this.ajaxAuthenticationSuccessHandler = ajaxAuthenticationSuccessHandler;
        this.ajaxAuthenticationFailureHandler = ajaxAuthenticationFailureHandler;
        this.ajaxLogoutSuccessHandler = ajaxLogoutSuccessHandler;
        this.ajaxAccessDeniedHandler = ajaxAccessDeniedHandler;
        this.selfAuthenticationProvider = selfAuthenticationProvider;
        this.lindTokenAuthenticationFilter = lindTokenAuthenticationFilter;
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

    private CorsConfigurationSource CorsConfigurationSource() {
        CorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");    //同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedHeader("*");//header，允许哪些header，本案中使用的是token，此处可将*替换为token；
        corsConfiguration.addAllowedMethod("*");    //允许的请求方法，PSOT、GET等
        ((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**", corsConfiguration); //配置允许跨域访问的url
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().cors().configurationSource(CorsConfigurationSource()).and()

                .httpBasic().authenticationEntryPoint(ajaxAuthenticationEntryPoint)

                .and()

                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/upload/file").permitAll()
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
        http.addFilterBefore(lindTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().accessDeniedHandler(ajaxAccessDeniedHandler); // 无权访问 JSON 格式的数据

    }
}
