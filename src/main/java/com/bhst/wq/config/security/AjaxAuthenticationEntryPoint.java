package com.bhst.wq.config.security;

import com.alibaba.druid.support.json.JSONUtils;
import com.bhst.wq.enumData.ExceptionEnum;
import com.bhst.wq.response.ResultBean;
import com.bhst.wq.utils.JsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultBean responseBody = new ResultBean();
        responseBody.setCode(ExceptionEnum.NOLOGIN.getCode());
        responseBody.setMsg(ExceptionEnum.NOLOGIN.getMsg());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(JsonUtils.toJson(responseBody));
    }
}
