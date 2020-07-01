package com.bhst.wq.config.security;

import com.alibaba.druid.util.StringUtils;
import com.bhst.wq.entity.WqUser;
import com.bhst.wq.service.WqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    WqUserService wqUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        WqUser wqUser = wqUserService.loginByUserName(username);
        if (null == wqUser) {
            throw new BadCredentialsException("未查到该用户");
        }
        if (StringUtils.isEmpty(wqUser.getPassword())) {
            wqUser.setPassword(encoder.encode("123"));
        }
        if (!encoder.matches(password, wqUser.getPassword())) {
            throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
        }

        return new UsernamePasswordAuthenticationToken(username, password, wqUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
