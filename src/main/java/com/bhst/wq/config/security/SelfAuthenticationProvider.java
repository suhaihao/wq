package com.bhst.wq.config.security;

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
import org.springframework.util.StringUtils;

@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    WqUserService wqUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String ps = encoder.encode(password);

        WqUser wqUser = wqUserService.loginByUserName(userName);
        if (!StringUtils.isEmpty(wqUser.getPassword())) {
            if (!wqUser.getPassword().equals(ps)) {
                throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
            }
        }

        return new UsernamePasswordAuthenticationToken(userName, password, wqUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
