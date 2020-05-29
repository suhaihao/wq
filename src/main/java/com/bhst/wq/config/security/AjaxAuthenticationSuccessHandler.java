package com.bhst.wq.config.security;

import com.alibaba.druid.support.json.JSONUtils;
import com.bhst.wq.enumData.ExceptionEnum;
import com.bhst.wq.response.ResultBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResultBean responseBody = new ResultBean();
        responseBody.setCode(ExceptionEnum.SUCCESS.getCode());
        responseBody.setMsg(ExceptionEnum.SUCCESS.getMsg());
        responseBody.setData(authentication);
        httpServletResponse.getWriter().write(JSONUtils.toJSONString(responseBody));
    }
}
